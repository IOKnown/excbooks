package com.excbooks.service.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.excbooks.dto.Image;
import com.excbooks.service.AmazonService;
import com.excbooks.service.FileType;
import com.excbooks.service.ImageService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class AmazonServiceImpl implements AmazonService {
    private static final Logger LOGGER = LogManager.getLogger(AmazonServiceImpl.class);
    private final String CREDENTIALS = "rootkey.csv";
    private final String PROFILE = "default";
    private final String BUCKET_NAME = "ioknown";

    @Autowired
    private ImageService imageService;

    public String addObject(File file, FileType type) {
        String folder = null;
        if (type == FileType.avatar) {
            folder = "avatar";
        } else if (type == FileType.book) {
            folder = "book";
        }
        Integer imgId = imageService.nextImgId();
        String extension = file.getName().substring(file.getName().indexOf('.'));
        String newFileName = String.format("%s/%s%s%s", folder, imgId, RandomStringUtils.randomAlphanumeric(8), extension);
        AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider(CREDENTIALS, PROFILE));

        try (InputStream inputStream = new FileInputStream(file)) {
            LOGGER.info("Uploading a new object to S3 from a file\n");
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.length());
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, newFileName, inputStream, metadata);
            s3client.putObject(putObjectRequest);

        } catch (AmazonServiceException ase) {
            LOGGER.error("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            LOGGER.error("Error Message:    " + ase.getMessage());
            LOGGER.error("HTTP Status Code: " + ase.getStatusCode());
            LOGGER.error("AWS Error Code:   " + ase.getErrorCode());
            LOGGER.error("Error Type:       " + ase.getErrorType());
            LOGGER.error("Request ID:       " + ase.getRequestId());

        } catch (AmazonClientException ace) {
            LOGGER.error("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            LOGGER.error("Error Message: " + ace.getMessage());

        } catch (FileNotFoundException e) {
            LOGGER.error("File with name" + file.getName() + "don't found", e);

        } catch (IOException e) {
            LOGGER.error(e);
        }
        return newFileName;
    }


    @Override
    public void deleteObject(Image img) {
        AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider(CREDENTIALS, PROFILE));
        try {
            s3Client.deleteObject(new DeleteObjectRequest(BUCKET_NAME, img.getKeyWithFolder()));
        } catch (AmazonServiceException ase) {
            LOGGER.error("Caught an AmazonServiceException.");
            LOGGER.error("Error Message:    " + ase.getMessage());
            LOGGER.error("HTTP Status Code: " + ase.getStatusCode());
            LOGGER.error("AWS Error Code:   " + ase.getErrorCode());
            LOGGER.error("Error Type:       " + ase.getErrorType());
            LOGGER.error("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            LOGGER.error("Caught an AmazonClientException.");
            LOGGER.error("Error Message: " + ace.getMessage());
        }
    }


    @Override
    public File getObject(Image img) {
        AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider(CREDENTIALS, PROFILE));
        File targetFile = null;
        try {
            LOGGER.info("Downloading an object");
            S3Object s3object = s3Client.getObject(new GetObjectRequest(BUCKET_NAME, img.getKeyWithFolder()));
            try (InputStream objectData = s3object.getObjectContent()) {
                targetFile = new File(img.getKey());
                FileUtils.copyInputStreamToFile(objectData, targetFile);
            } catch (IOException e) {
                LOGGER.error(e);
            }

        } catch (AmazonServiceException ase) {
            LOGGER.error("Caught an AmazonServiceException, which" +
                    " means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            LOGGER.error("Error Message:    " + ase.getMessage());
            LOGGER.error("HTTP Status Code: " + ase.getStatusCode());
            LOGGER.error("AWS Error Code:   " + ase.getErrorCode());
            LOGGER.error("Error Type:       " + ase.getErrorType());
            LOGGER.error("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            LOGGER.error("Caught an AmazonClientException, which means" +
                    " the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            LOGGER.error("Error Message: " + ace.getMessage());
        }
        return targetFile;
    }
}
