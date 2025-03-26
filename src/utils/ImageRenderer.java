package utils;

import java.io.IOException;

public class ImageRenderer {

    public static void generateImage(String dotFile, String outputImage) {
        try {
            ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", dotFile, "-o", outputImage);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to generate image: " + e.getMessage());
        }
    }
}
