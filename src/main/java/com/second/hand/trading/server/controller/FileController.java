package com.second.hand.trading.server.controller;

import com.second.hand.trading.server.enums.ErrorMsg;
import com.second.hand.trading.server.service.FileService;
import com.second.hand.trading.server.utils.IdFactoryUtil;
import com.second.hand.trading.server.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
@CrossOrigin
@RestController
public class FileController {

    @Value("${userFilePath}")
    private String userFilePath;

    @Value("${baseUrl}")
    private String baseUrl;

    @Autowired
    private FileService fileService;

    @PostMapping("/file")
    public ResultVo uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        String uuid="file"+ IdFactoryUtil.getFileId();
        String fileName= uuid+ multipartFile.getOriginalFilename();
        try {
            if (fileService.uploadFile(multipartFile,fileName)) {
                return ResultVo.success(baseUrl+"/image?imageName="+fileName);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
        }
        return ResultVo.fail(ErrorMsg.FILE_UPLOAD_ERROR);
    }

    @GetMapping("/image")
    public void getImage(@RequestParam("imageName") String imageName,
                         HttpServletResponse response) throws IOException {
        File fileDir = new File(userFilePath);
        File image=new File(fileDir.getAbsolutePath() +"/"+imageName);
        if (image.exists()){
            FileInputStream fileInputStream=new FileInputStream(image);
            byte[] bytes=new byte[fileInputStream.available()];
            if (fileInputStream.read(bytes)>0){
                OutputStream outputStream=response.getOutputStream();
                outputStream.write(bytes);
                outputStream.close();
            }
            fileInputStream.close();
        }
    }

}
//这段代码是一个Java类，名为`FileController`，它是一个控制器类，用于处理与文件上传和下载相关的HTTP请求。下面对这段代码进行解析：
//
//        1. **注解**：
//
//
//        * `@CrossOrigin：允许来自不同源的跨源请求。
//        * `@RestController`：标记这个类是一个RESTful控制器，所有的方法返回的对象都会被序列化为JSON格式。
//        2. **字段**：
//
//
//        * `private String userFilePath;`：文件上传路径。
//        * `private String baseUrl;`：读取基本地址，它指向实际文件存储的位置。
//        * `private FileService fileService;`：文件服务对象，用于实际的文件操作。
//        3. **方法**：
//
//
//        * `uploadFile`：一个POST请求方法，用于上传文件。它接受一个文件类型的参数，并返回上传结果在方法中生成一个随机唯一的文件名，并使用`fileService`的`uploadFile`方法来上传文件。
//        * `getImage`：一个GET请求方法，用于获取图片文件。它接受一个文件名参数(imageName)，并将文件发送到响应流中。
//
//        这些方法中使用了注解和文件服务对象，以实文件上传和下载的功能。其中`uploadFile`方法根据配置文件中的文件路径，将传入的文件上传到指定位置，返回上传成功后的文件名和基本地址。`getImage`方法则根据文件名，将文件从指定位置读取出来，并将其响应到输出流中，用于在网页中显示。
