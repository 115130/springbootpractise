package com.example.android.util;

import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtil {



        /**
         * 加解密密钥, 外部可以
         */
        public static final String AES_DATA_SECURITY_KEY = "4%YkW!@g5LGcf9Ut";
        /**
         * 算法/加密模式/填充方式
         */
        private static final String AES_PKCS5P = "AES/ECB/PKCS5Padding";

        private static final String AES_PERSON_KEY_SECURITY_KEY = "pisnyMyZYXuCNcRd";

        /**
         * 加密
         *
         * @param str
         *            需要加密的字符串
         * @param key
         *            密钥
         * @return
         * @throws Exception
         */
        private static String encrypt(String str, String key) {
            if (Strings.isEmpty(key)) {
                throw new RuntimeException("key不能为空");
            }
            try {
                if (str == null) {
                    return null;
                }
                // 判断Key是否为16位
                if (key.length() != 16) {
                    return null;
                }
                byte[] raw = key.getBytes("UTF-8");
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                // "算法/模式/补码方式"
                Cipher cipher = Cipher.getInstance(AES_PKCS5P);
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
                byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
                // 此处使用BASE64做转码功能，同时能起到2次加密的作用。
                return new BASE64Encoder().encode(encrypted);
            } catch (Exception ex) {
                return null;
            }

        }

        /**
         * 解密
         *
         * @param str 需要解密的字符串
         * @param key 密钥
         * @return
         */
        private static String decrypt(String str, String key) {
            if (Strings.isEmpty(key)) {
                throw new RuntimeException("key不能为空");
            }
            try {
                if (str == null) {
                    return null;
                }
                // 判断Key是否为16位
                if (key.length() != 16) {
                    return null;
                }
                byte[] raw = key.getBytes("UTF-8");
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance(AES_PKCS5P);
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
                // 先用base64解密
                byte[] encrypted = new BASE64Decoder().decodeBuffer(str);
                try {
                    byte[] original = cipher.doFinal(encrypted);
                    String originalString = new String(original, "UTF-8");
                    return originalString;
                } catch (Exception e) {
                    return null;
                }
            } catch (Exception ex) {
                return null;
            }
        }

        /**
         * 加密
         *
         * @param str 需要加密的字符串
         * @return
         * @throws Exception
         */
        public static String encrypt(String str) {
            if (str==null){
                return null;
            }
            for (int i = 0; i < 30; i++) {
                str=encrypt(str,AES_DATA_SECURITY_KEY);
            }
            str=str.substring(0,20);
            return str;
        }

        /**
         * 解密
         * @param str 需要解密的字符串
         * @return
         */
        public static String decrypt(String str) {
            return decrypt(str,AES_DATA_SECURITY_KEY);
        }





}
