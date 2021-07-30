package com.pdf;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * Hello world!
 *
 */
public class App {
    private static PDDocument document = new PDDocument();
    private static byte[] pdfData;

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");

        // PDF Base64 Encoded to binary pdf data
        // String encoded = FileUtils.readFileToString(new File("src/res/test.txt"), "UTF-8");
        // pdfData = Base64.getDecoder().decode(encoded);
        pdfData = FileUtils.readFileToByteArray(new File("src/res/test.pdf"));
        
        try{
            document = PDDocument.load(pdfData);
            if (!document.isEncrypted()) {
                PDFTextStripper stripper = new PDFTextStripper();
                String pdfText = stripper.getText(document);
                System.out.println("Text:" + pdfText);
                FileUtils.writeStringToFile(new File("src/res/test_text.txt"), pdfText, "UTF-8");
            }
        }
        finally{
            document.close();
        }
        
    }
}
