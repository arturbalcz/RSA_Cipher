package rsa;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import rsa.keys.KeyGenerator;

public class RSA_Cipher {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss_SSS");
    private static KeyGenerator generator = new KeyGenerator();

    private static String readFromFile(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
            String text = lines.stream().map(Object::toString).collect(Collectors.joining());
            return text; 
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ""; 
    }

    private static void writeBytesToFile(byte[] bytes, String filename) {
        try {
            Files.write(Paths.get(filename), bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void encryptAndDecryptFile(String filename) {
        System.out.println("Reading from file: " + filename);
        byte[] text = readFromFile(filename).getBytes(); 
        String[] filenameAndType = splitFilenameAndType(filename); 

        System.out.println("Encrypting...");
        byte[] encrypted = Encrypter.encrypt(generator.getPublicKey(), text);

        String encryptedFilename = filenameAndType[0] + "_encrypted_" + LocalDateTime.now().format(formatter) + "." + filenameAndType[1]; 
        System.out.println("Writing to file: " + encryptedFilename);
        writeBytesToFile(encrypted, encryptedFilename);

        System.out.println("Decrypting...");
        byte[] decrypted = Decrypter.decrypt(generator.getPrivateKey(), encrypted);

        String decryptedFilename = filenameAndType[0] + "_decrypted_" + LocalDateTime.now().format(formatter) + "." + filenameAndType[1]; 
        System.out.println("Writing to file: " + decryptedFilename);
        writeBytesToFile(decrypted, decryptedFilename); 
    }

    private static String[] splitFilenameAndType(String filename) {
        String[] result = filename.split("\\.");
        return result; 
    }

    public static void main(String[] args) {
        System.out.println("Generating keys...");

        for (int i = 0; i < args.length; i++) {
            encryptAndDecryptFile(args[i]);
        }

        System.out.println("Program finished");
    }
}