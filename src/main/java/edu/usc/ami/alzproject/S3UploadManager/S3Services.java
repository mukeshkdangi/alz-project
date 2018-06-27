package edu.usc.ami.alzproject.S3UploadManager;

public interface S3Services {

    public void downloadFile(String keyName);

    public void uploadFile(String keyName, String uploadFilePath);

    public void createBucket(String bucketName);
}
