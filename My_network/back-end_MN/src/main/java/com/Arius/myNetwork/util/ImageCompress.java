///**
// * @author arashxr
// * @email ariuschamreun15@gmail.com
// * @github <a href="https://github.com/Arius-Chamreun">Github Url</a>
// */
//package com.Arius.myNetwork.util;
//
//import jakarta.persistence.AttributeConverter;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//
//public class ImageCompress implements AttributeConverter<byte[], byte[]> {
//
//
//    @Override
//    public byte[] convertToDatabaseColumn(byte[] attribute) {
//        try {
//            return compressImage(attribute);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public byte[] convertToEntityAttribute(byte[] dbData) {
//        try {
//            return decompressImage(dbData);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}
//
