package com.study.util.code;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class HmacRipeMDCoder {
    /**
     * 初始化HmacRipeMD128密钥
     * @return byte[] 密钥
     * @throws Exception
     */
    public static byte[] initHmacRipeMD128Key() throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacRipeMD128");
        // 产生秘密密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获得密钥
        return secretKey.getEncoded();
    }

    /**
     * HmacRipeMD128消息摘要
     * @param data 待做消息摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeHmacRipeMD128(byte[] data, byte[] key) throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacRipeMD128");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要
        return mac.doFinal(data);
    }

    /**
     * HmacRipeMD128Hex消息摘要
     * @param data 待做消息摘要处理的数据
     * @param key 密钥
     * @return String 消息摘要
     * @throws Exception
     */
    public static String encodeHmacRipeMD128Hex(byte[] data, byte[] key) throws Exception {
        // 执行消息摘要
        byte[] b = encodeHmacRipeMD128(data, key);
        // 做十六进制转换
        return new String(Hex.encode(b));
    }

    /**
     * 初始化HmacRipeMD160密钥
     * @return byte[] 密钥
     * @throws Exception
     */
    public static byte[] initHmacRipeMD160Key() throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacRipeMD160");
        // 产生秘密密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获得密钥
        return secretKey.getEncoded();
    }

    /**
     * HmacRipeMD160消息摘要
     * @param data 待做消息摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeHmacRipeMD160(byte[] data, byte[] key) throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacRipeMD160");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要
        return mac.doFinal(data);
    }

    /**
     * HmacRipeMD160Hex消息摘要
     * @param data 待做消息摘要处理的数据
     * @param key 密钥
     * @return String 消息摘要
     * @throws Exception
     */
    public static String encodeHmacRipeMD160Hex(byte[] data, byte[] key) throws Exception {
        // 执行消息摘要
        byte[] b = encodeHmacRipeMD160(data, key);
        // 做十六进制转换
        return new String(Hex.encode(b));
    }


    /**
     * 测试HmacRipeMD128
     * @throws Exception
     */
    @Test
    public final void testEncodeHmacRipeMD128() throws Exception {
        String str = "HmacRipeMD128消息摘要";
        // 初始化密钥
        byte[] key = HmacRipeMDCoder.initHmacRipeMD128Key();
        // 获得摘要信息
        byte[] data1 = HmacRipeMDCoder.encodeHmacRipeMD128(str.getBytes(), key);
        byte[] data2 = HmacRipeMDCoder.encodeHmacRipeMD128(str.getBytes(), key);
        // 校验
        assertArrayEquals(data1, data2);
    }
    /**
     * 测试HmacRipeMD128Hex
     * @throws Exception
     */
    @Test
    public final void testEncodeHmacRipeMD128Hex() throws Exception {
        String str = "HmacRipeMD128Hex消息摘要";
        // 初始化密钥
        byte[] key = HmacRipeMDCoder.initHmacRipeMD128Key();
        // 获得摘要信息
        String data1 = HmacRipeMDCoder.encodeHmacRipeMD128Hex(str.getBytes(), key);
        String data2 = HmacRipeMDCoder.encodeHmacRipeMD128Hex(str.getBytes(), key);
        System.err.println("原文：\t" + str);
        System.err.println("HmacRipeMD128Hex-1：\t" + data1);
        System.err.println("HmacRipeMD128Hex-2：\t" + data2);
        // 校验
        assertEquals(data1, data2);
    }
    /**
     * 测试HmacRipeMD160
     * @throws Exception
     */
    @Test
    public final void testEncodeHmacRipeMD160() throws Exception {
        String str = "HmacRipeMD160消息摘要";
        // 初始化密钥
        byte[] key = HmacRipeMDCoder.initHmacRipeMD160Key();
        // 获得摘要信息
        byte[] data1 = HmacRipeMDCoder.encodeHmacRipeMD160(str.getBytes(), key);
        byte[] data2 = HmacRipeMDCoder.encodeHmacRipeMD160(str.getBytes(), key);
        // 校验
        assertArrayEquals(data1, data2);
    }

    /**
     * 测试HmacRipeMD160Hex
     * @throws Exception
     */
    @Test
    public final void testEncodeHmacMD4Hex() throws Exception {
        String str = "HmacRipeMD160Hex消息摘要";
        // 初始化密钥
        byte[] key = HmacRipeMDCoder.initHmacRipeMD160Key();
        // 获得摘要信息
        String data1 = HmacRipeMDCoder.encodeHmacRipeMD160Hex(str.getBytes(), key);
        String data2 = HmacRipeMDCoder.encodeHmacRipeMD160Hex(str.getBytes(), key);
        System.err.println("原文：\t" + str);
        System.err.println("HmacRipeMD160Hex-1：\t" + data1);
        System.err.println("HmacRipeMD160Hex-2：\t" + data2);
        // 校验
        assertEquals(data1, data2);
    }

}
