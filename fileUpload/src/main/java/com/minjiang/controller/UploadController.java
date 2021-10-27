package com.minjiang.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * @auther guannw
 * @create 2021/8/28 13:01
 */

@Controller
public class UploadController {

    private final static String utf8 = "utf-8";

    @RequestMapping("/upload")
    @ResponseBody
    public void upload(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "upload",required=false) MultipartFile multipartFile){
        //文件分片
        //定义响应编码
        response.setCharacterEncoding(utf8);
        String uploadPath = "F:\\fileItem";//文件临时存放目录
        BufferedOutputStream os = null;//文件合并流
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //获取一起跟文件传过来的其他参数值
        Integer schunk = Integer.valueOf(multipartRequest.getParameter("chunk"));//记录当前分片数
        Integer schunks = Integer.valueOf(multipartRequest.getParameter("chunks"));//记录总分片数
        String name = multipartRequest.getParameter("name");//文件名字

        try{

            String tempFileName = name;//设置临时文件名，没有分片的话直接使用文件名
            if(name != null){//文件名字不能为空
                if(schunk != null){
                    tempFileName = schunk + "_" + name;//如果有分片需要重新定义临时文件名
                }

                File tempFile = new File(uploadPath,tempFileName);//生成临时文件
                if(!tempFile.exists()){//判断文件是否存在,也就是断点续传
                    multipartFile.transferTo(tempFile);//写入文件
                }
            }

            //文件合并
            if(schunk != null && schunk.intValue() == schunks.intValue()-1){//判断是否有分片，以及判断是否是最后一个分片
                File tempFile = new File(uploadPath,name);
                os = new BufferedOutputStream(new FileOutputStream(tempFile));//把每一个文件分片都往这个文件里面去写
                for (int i = 0 ; i < schunks; i++){
                    File file = new File(uploadPath,i + "_"+name);//临时文件
                    while (!file.exists()){
                        Thread.sleep(100);
                    }
                    byte[] bytes = FileUtils.readFileToByteArray(file);
                    os.write(bytes);
                    os.flush();//文件流写入
                    file.delete();//删除临时文件
                }
                os.flush();
            }
            response.getWriter().write("上传成功"+name);

        } catch ( UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(os != null){
                    os.close();//最后需要把流进行关闭
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }
}
