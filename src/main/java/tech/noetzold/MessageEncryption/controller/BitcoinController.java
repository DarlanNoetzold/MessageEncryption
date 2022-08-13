package tech.noetzold.MessageEncryption.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class BitcoinController {

    @GetMapping("/show")
    public String showHomePage(){
        return "index.html";
    }

    public static String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
        return convertByteArrayToHexString(hash);
    }

    public static String convertByteArrayToHexString(byte[] arrayBytes){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte i: arrayBytes) stringBuilder.append(Integer.toString((i & 0xff) + 0x100, 16).substring(1));
        return stringBuilder.toString();
    }

    public StringBuilder mine(int blockNumber, String transaction, String previousHash) throws NoSuchAlgorithmException {
        StringBuilder fin = new StringBuilder();
        int nonce = 0;
        for(int i = 0; i< 100; nonce++){
            String text = blockNumber + transaction + previousHash + nonce;
            String newHash = sha256(text);
            if(newHash.startsWith("00", 0)){
                fin.append(newHash);
                System.out.println(newHash);
            }
            System.out.println(nonce);
            continue;
        }
        return fin;
    }
}
