package com.reststay.restaurant_service.core.utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtility {
//     Base64.getEncoder().encodeToString(imageBytes)
//    public static byte[] compressImage(byte[] data) {
//
//        Deflater deflater = new Deflater();
//        deflater.setLevel(Deflater.BEST_COMPRESSION);
//        deflater.setInput(data);
//        deflater.finish();
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//
//        byte[] tmp = new byte[4 * 1024];
//        while (!deflater.finished()) {
//            int size = deflater.deflate(tmp);
//            outputStream.write(tmp, 0, size);
//        }
//        try {
//            outputStream.close();
//        } catch (Exception e) {
//        }
//        return outputStream.toByteArray();
//    }
//
//    public static String compressAndEncodeImage(byte[] data) {
//        if (data == null || data.length == 0) {
//            throw new IllegalArgumentException("Input data cannot be null or empty.");
//        }
//
//        // Sıkıştırma
//        Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
//        deflater.setInput(data);
//        deflater.finish();
//
//        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length / 2)) {
//            byte[] tmp = new byte[4 * 1024];
//            while (!deflater.finished()) {
//                int size = deflater.deflate(tmp);
//                outputStream.write(tmp, 0, size);
//            }
//            // Base64 kodlama
//            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
//        } catch (Exception e) {
//            throw new RuntimeException("Error occurred while compressing and encoding image data", e);
//        } finally {
//            deflater.end();
//        }
//    }

    public static byte[] compressImage(byte[] data) {
        // Örnek: JPEG sıkıştırması
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            BufferedImage image = ImageIO.read(bais);
            ImageIO.write(image, "jpg", baos); // JPEG formatına dönüştür
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error during image compression", e);
        }
    }
//
//    public static byte[] compressImage(byte[] data) {
//        if (data == null || data.length == 0) {
//            throw new IllegalArgumentException("Input data cannot be null or empty.");
//        }
//
//        // Sıkıştırma
//        Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
//        deflater.setInput(data);
//        deflater.finish();
//
//        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length / 2)) {
//            byte[] tmp = new byte[4 * 1024];
//            while (!deflater.finished()) {
//                int size = deflater.deflate(tmp);
//                outputStream.write(tmp, 0, size);
//            }
//            // Sıkıştırılmış veriyi byte[] olarak geri döndür
//            return outputStream.toByteArray();
//        } catch (Exception e) {
//            throw new RuntimeException("Error occurred while compressing image data", e);
//        } finally {
//            deflater.end();
//        }
//    }
//


    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception exception) {
        }
        return outputStream.toByteArray();
    }
}
