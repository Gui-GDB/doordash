package com.gdb.doordash.controller.web;

import com.gdb.doordash.constant.MessageConstant;
import com.gdb.doordash.result.Result;
import com.gdb.doordash.utils.AliOssUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/web/common")
@Tag(name = "通用接口")
@Slf4j
public class CommonController {

    @Resource
    private AliOssUtil aliOssUtil;

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    @Operation(summary = "文件上传")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传：{}", file);

        try {
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件名的后缀   xxx.png
            assert originalFilename != null;
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新文件名称
            String objectName = UUID.randomUUID() + extension;
            //文件的请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e.getMessage());
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}