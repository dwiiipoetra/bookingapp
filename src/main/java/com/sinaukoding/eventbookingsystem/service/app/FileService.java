package com.sinaukoding.eventbookingsystem.service.app;

import com.sinaukoding.eventbookingsystem.model.enums.TipeUpload;
import com.sinaukoding.eventbookingsystem.model.response.BaseResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    BaseResponse<?> upload(MultipartFile files, TipeUpload tipeUpload);

    Resource loadFileAsResource(String pathFile);
}
