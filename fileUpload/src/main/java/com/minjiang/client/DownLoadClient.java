package com.minjiang.client;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther guannw
 * @create 2021/9/2 9:06
 */

@RestController
public class DownLoadClient {

    private final static  long PER_PAGE = 1024l * 1024l * 50l;//定义每一个分片大小

    private final static  String DOWN_PATH = "F:\\fileDownTest";//文件下载位置

    ExecutorService pool = Executors.newFixedThreadPool(10);//设定10个大小的固定线程池


    //探测下载 获取文件信息 例如 文件大小 文件名称
    //多线程文件下载
    //最后一个文件下载完之后， 开始合并
    @RequestMapping(value = "/downLoadFile")
    public String downLoadFile() throws IOException, InterruptedException {
        FileInfo fileInfo = download(0, 10, -1, null);//探测下载获得文件信息

        if (fileInfo != null){//可能文件已经存在所以可能返回值为空 ,所以需要对文件进行非空判断
            long pages = fileInfo.fileSize / PER_PAGE;//计算需要分片的数量
            for (long i = 0 ; i <= pages ; i++){
                pool.submit(new DownLoad(i * PER_PAGE,(i+1) * PER_PAGE -1,i,fileInfo.fileName ));
            }


        }
        return "success";
    }

    class DownLoad implements Runnable{

        long start;
        long end;
        long page;
        String fName;

        public DownLoad(long start, long end, long page, String fName) {
            this.start = start;
            this.end = end;
            this.page = page;
            this.fName = fName;
        }

        @Override
        public void run() {
            try {
                download(start, end, page, fName);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class FileInfo{
        long fileSize;
        String fileName;

        public FileInfo(long fileSize, String fileName) {
            this.fileSize = fileSize;
            this.fileName = fileName;
        }
    }

    //文件起始位置和文件结束位置
     private FileInfo download(long start , long end , long page ,String fName) throws IOException, InterruptedException {//文件 开始位置 结束位置 第几个分片 临时文件吗

        File file = new File(DOWN_PATH,page+"-"+fName);
         //断点下载
        if(file.exists() && page != -1 && file.length() == PER_PAGE){//文件存在 以及不是探测下载和文件不完整（即文件大小不符合分片大小）
            return null;
        }

         HttpClient httpClient = HttpClients.createDefault();//创建一个http客户端
         HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/download");
         httpGet.setHeader("Range","bytes="+start+"-"+end);//设置分片下载头部信息 Range bytes=起始位置-结束位置
         HttpResponse response = httpClient.execute(httpGet);//发出Http get请求
         String fSize = response.getFirstHeader("fSize").getValue();
         fName = URLEncoder.encode(response.getFirstHeader("fName").getValue(),"utf-8");
         HttpEntity entity = response.getEntity();
         InputStream is = entity.getContent();

         FileOutputStream fos = new FileOutputStream(file);
         byte[] buffer  = new byte[1024];
         int ch ;//偏移量
         while((ch = is.read(buffer)) != -1){
             fos.write(buffer,0,ch);
         }
         is.close();
         fos.flush();
         fos.close();
         if ((end - Long.valueOf(fSize)) > 0){//最后一个分片到达的时候进行文件合并
            mergeFile(fName,page);
         }
         return new FileInfo(Long.parseLong(fSize),fName);

     }

    private void mergeFile(String fName, long page) throws IOException, InterruptedException {
        File file = new File(DOWN_PATH,fName);
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));

        for (int i = 0; i <= page ; i++ ){
            File fileTemp = new File(DOWN_PATH,i+"-"+fName);
            while (!fileTemp.exists() || (i != page && fileTemp.length() < PER_PAGE)){
                Thread.sleep(100);
            }
            byte[] bytes = FileUtils.readFileToByteArray(fileTemp);
            os.write(bytes);
            os.flush();
            fileTemp.delete();
        }
        File file1 = new File(DOWN_PATH,-1+"-null");
        file1.delete();//删除探测文件
        os.flush();
        os.close();
    }
}
