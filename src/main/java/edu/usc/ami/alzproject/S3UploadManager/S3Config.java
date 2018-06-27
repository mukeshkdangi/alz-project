package edu.usc.ami.alzproject.S3UploadManager;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class S3Config {

    static {
        createS3client();
    }

    private static void init() {
    }

    static S3Config s3Config;
    private static AWSCredentials credentials = new ProfileCredentialsProvider().getCredentials();


    public static AmazonS3 s3Client;


    @Value("${jsa.aws.access_key_id}")
    private String awsId;

    @Value("${jsa.aws.secret_access_key}")
    private String awsKey;

    @Value("${jsa.s3.region}")
    private String region;


    public static AmazonS3 createS3client() {

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(credentials.getAWSSecretKey(), credentials.getAWSAccessKeyId());
        s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Region.getRegion(Regions.US_EAST_1).getName())
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
        return s3Client;
    }

}
