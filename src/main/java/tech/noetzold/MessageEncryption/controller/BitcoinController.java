package tech.noetzold.MessageEncryption.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.noetzold.MessageEncryption.model.Message;
import tech.noetzold.MessageEncryption.repository.MessageRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class BitcoinController {

    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/show")
    public String showHomePage(){
        return "index.html";
    }

    @PostMapping("/send")
    public String sendMessage(Model model, @RequestParam Message user, @RequestParam String message) throws NoSuchAlgorithmException {
        String encrypt = sha256(message);

        Message message1 = new Message();
        message1.setId(message1.getId());
        message1.setMessage(encrypt);

        model.addAttribute("save", messageRepository.save(message1));
        return "index";
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
