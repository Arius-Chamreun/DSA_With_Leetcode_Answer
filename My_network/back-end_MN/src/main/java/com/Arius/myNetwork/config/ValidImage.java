/**
 * @author arashxr
 * @email ariuschamreun15@gmail.com
 * @github <a href="https://github.com/Arius-Chamreun">Github Url</a>
 */
package com.Arius.myNetwork.config;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class ValidImage {
   private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("PNG","AVIF", "JPG", "JPEG", "SVG");
    public static boolean isValid(MultipartFile image){
        String extension = FilenameUtils.getExtension(image.getName());
        return ALLOWED_EXTENSIONS.contains(extension.toUpperCase());
    }
}
