package edu.usc.ami.alzproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.usc.ami.alzproject.S3UploadManager.S3Config;
import edu.usc.ami.alzproject.Utility.S3Utility;

@RestController
@RequestMapping("/")

public class BucketController {

    private S3Config amazonClient;

    @Autowired
    BucketController(S3Config amazonClient) {

        this.amazonClient = amazonClient;
    }

    @PostMapping("uploadFile")
    public void uploadFile(@RequestPart(value = "dirPath") String path, @RequestPart(value = "user") String user) {
        S3Utility.uploadDirectory(path, user);
    }

}
