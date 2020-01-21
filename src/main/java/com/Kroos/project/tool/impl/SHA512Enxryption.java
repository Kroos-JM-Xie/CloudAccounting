package com.Kroos.project.tool.impl;

import com.Kroos.project.enums.CodeAndMessage;
import com.Kroos.project.exception.EncryptException;
import com.Kroos.project.tool.Encryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 加密工具
 * @author Kroos
 */
public class SHA512Enxryption implements Encryption {
    private final static Logger log= LoggerFactory.getLogger(SHA512Enxryption.class);
    /**
     * 生成随机盐，128个字符 盐的长度等同于加密后字符串长度
     *
     * @return 随机字符串
     */
    public String createSalt() {
        char[] chars = "12WX39ab0cdefQRSLMNOPghijklmnoABCpquvz678xyzDEFGHI45JKTrwtUVYZ".toCharArray();
        char[] saltchars = new char[128];
        Random random = new SecureRandom();
        for (int i = 0; i < saltchars.length; i++) {
            int n = random.nextInt(62);
            saltchars[i] = chars[n];
        }
        String salt = new String(saltchars);
        return salt;
    }

    /**
     * SHA512加密实现(128个字符)
     *
     * @param password
     * @return 加密后的字符串
     */
    @Override
    public String encrypt(String password) {
        String shaPwd = null;
        if (password != null && password.length() > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
                messageDigest.update(password.getBytes());
                byte byteBuffer[] = messageDigest.digest();
                StringBuffer strHexString = new StringBuffer();
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                shaPwd = strHexString.toString();
                log.info("加密成功！");
            } catch (Exception e) {
                shaPwd=null;
                e.printStackTrace();
                log.error("加密发生异常:\n"+e.getMessage());
                throw new EncryptException(CodeAndMessage.Password_encrypt_fail);
            }
        }
        return shaPwd;
    }

}
