package com.minjiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @auther guannw
 * @create 2021/8/30 11:21
 */

@Controller
public class DownLoadController {
    private final static String utf8 = "utf-8";

    @RequestMapping(value="/download")
    public void downLoadFile(HttpServletRequest request , HttpServletResponse response){
        File file = new File("F:\\fileItem\\Idea 2019.zip");//自定义一个需要下载的文件
        response.setCharacterEncoding(utf8);//设置响应编码
        InputStream is = null;//输入文件流
        OutputStream os = null;//输出文件流

        try{
            //分片下载
            long fSize = file.length();
            response.setContentType("application/x-download");//告知前端这是一个下载请求
            String fileName = URLEncoder.encode(file.getName(),utf8);//设置文件名，预防中文乱码
            response.addHeader("Content-Disposition","attachment;filename="+fileName);
            response.setHeader("Accept-Range","bytes");//告知前端支持分片操作

            response.setHeader("fSize",String.valueOf(fSize));//告知客户端文件大小有多大
            response.setHeader("fName",fileName);//告知客户端文件大小有多大
            long pos = 0;  //设置文件分片pos 起始位置 last 结束位置 sum 总数
            long last = fSize - 1;
            long sum = 0;
            if(null != request.getHeader("Range")){//判断前端是否需要进行分片下载
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);// SC_PARTIAL_CONTENT 告知前端是进行分片下载

                //从Header 中获取 Range 信息 格式： bytes=1000-10000  也就是分片数量从1000到10000所以我们截取字符串
                //bytes=100-   意思是100到末尾
                String numRange = request.getHeader("Range").replaceAll("bytes=", "");
                String[] strRange = numRange.split("-");//拆分数组
                if(strRange.length == 2){
                    pos = Long.parseLong(strRange[0].trim());//设置起始位置
                    last = Long.parseLong(strRange[1].trim());//设置结束位置
                    if(fSize < last){//最后一片的时候很可能超出文件大小，因此需要判断结束位置是否超出文件大小
                        last = fSize-1;
                    }
                }else {//如果分片是从起始位置到结束，也就是我们从Range 拿到的信息是 bytes=100-这样的格式
                    pos = Long.parseLong(numRange.replaceAll("-","").trim());//设置起始位置
                    last = fSize-1;
                }
            }
            long rangeLength = last - pos +1;//获取文件需要读取的文件大小，也就是字节数
            String contentRange = new StringBuffer("bytes ").append(pos).append("-").append(last).append("/").append(fSize).toString();
            response.setHeader("Content-Range",contentRange);
            response.setHeader("Content-Length", String.valueOf(rangeLength));

            os = new BufferedOutputStream(response.getOutputStream());
            is = new BufferedInputStream(new FileInputStream(file));
            is.skip(pos);//跳转到我们需要的起始位置流
            byte[] buffer = new byte[1024];
            int length = 0;
            while (sum < rangeLength){
                length = is.read(buffer,0, (rangeLength - sum)<= buffer.length ? ((int) ( rangeLength - sum)) : buffer.length);
                sum += length;
                os.write(buffer,0,length);
            }
            System.out.println("下载完成");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭输入输出流
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
