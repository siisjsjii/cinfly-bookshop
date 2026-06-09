package com.cinfly.controller;

import com.cinfly.constant.Result;
import com.cinfly.utils.AliOssUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
@Slf4j
@Tag(name = "文件上传相关接口")
public class UploadController {
    @Autowired
    private AliOssUtil aliOssUtil;
    @PostMapping
    public Result<String> upload(MultipartFile file) throws IOException {
        log.info("正在进行文件上传:{}",file.getOriginalFilename());
        String dir= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        String name= UUID.randomUUID().toString()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newFileName=dir+"/"+name;
        String url = aliOssUtil.upload(file.getBytes(), newFileName);
        log.info("文件上传完成:{}",url);
        return Result.success(url);
    }

}
