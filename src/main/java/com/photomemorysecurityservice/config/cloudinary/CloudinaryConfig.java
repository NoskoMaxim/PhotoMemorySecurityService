package com.photomemorysecurityservice.config.cloudinary;

import com.cloudinary.utils.ObjectUtils;

import java.util.Map;

public class CloudinaryConfig {
    public static Map getCloudinaryConfiguration(){
        return ObjectUtils.asMap(
                "container", "PhotoMemorySecurityApp",
                "cloud_name", "photomemorysecurityapp",
                "api_key", "173499496539899",
                "api_secret", "7TMZZbvJ8RwhyJiusZsHtL0te2Q"
        );
    }
}