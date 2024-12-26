package com.huiyi.huiyiproject.controller.minio;

import com.huiyi.huiyiproject.entity.FileEntity;
import com.huiyi.huiyiproject.service.FileService;
import com.huiyi.huiyiproject.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * minio
 */
@RestController
@RequestMapping("/minioFile")
public class MinioFileController {

    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private FileService filesService;



    /**
     * 上传文件接口
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String originalName  = file.getOriginalFilename();
            //上传到minio并获取minio中的访问url
            String uniqueName = minioUtil.uploadFile(originalName, file.getInputStream(), file.getContentType());
            String fileUrl = minioUtil.getFileUrl(uniqueName);
            //保存到数据库
            FileEntity fileEntity = new FileEntity();
            fileEntity.setName(originalName);
            fileEntity.setUniqueName(uniqueName);
            fileEntity.setType(file.getContentType());
            fileEntity.setUrl(fileUrl);
            filesService.createOne(fileEntity);

            return ResponseEntity.ok("文件上传成功，访问 URL: " + fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("文件上传失败");
        }
    }

    /**
     * 下载文件接口
     */
    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("uniqueName") String uniqueName) {
        try {
            // 获取文件输入流
            InputStreamResource resource = new InputStreamResource(minioUtil.downloadFile(uniqueName));

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + uniqueName);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * 删除文件接口
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFile(@RequestParam("uniqueName") String uniqueName) {
        try {
            //删除minio中文件
            minioUtil.deleteFile(uniqueName);
            //删除数据库记录
            int i = filesService.deleteByUniqueName(uniqueName);
            return ResponseEntity.ok("文件删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("文件删除失败");
        }
    }
}
