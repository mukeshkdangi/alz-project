package edu.usc.ami.alzproject.Utility;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import edu.usc.ami.alzproject.S3UploadManager.S3Config;

public class S3Utility {

    static AmazonS3 s3client = S3Config.s3Client;

    public void createFolder(String bucketName, String folder, String SFIX) {

        // create meta-data for your folder and set content-length to 0
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);
        // create empty content
        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
        // create a PutObjectRequest passing the folder name suffixed by /
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
                folder + SFIX, emptyContent, metadata);
        // send request to S3 to create folder
        s3client.putObject(putObjectRequest);
    }

    public void createBucket(String bucketName) {
        s3client.createBucket(bucketName);
    }

    /**
     * @param dir_path
     * @param bucketName
     */

    public static void uploadDirectory(String dir_path, String bucketName) {
        TransferManager xfer_mgr = TransferManagerBuilder.standard().withS3Client(s3client).build();
        try {
            MultipleFileUpload xfer = xfer_mgr.uploadDirectory(bucketName, "/"
                    , new File(dir_path), true);
            XferMgrProgress.showTransferProgress(xfer);
            XferMgrProgress.waitForCompletion(xfer);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
    }

}
