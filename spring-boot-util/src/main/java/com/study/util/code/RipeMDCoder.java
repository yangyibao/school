package com.study.util.code;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.Security;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RipeMDCoder {
    /**
     * RipeMD128消息摘要
     *
     * @param data 待做消息摘要处理的数据
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeRipeMD128(byte[] data) throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("RipeMD128");
        // 执行消息摘要
        return md.digest(data);
    }

    /**
     * RipeMD128Hex消息摘要
     *
     * @param data 待做消息摘要处理的数据
     * @return byte[] 消息摘要
     * @throws Exception 196 Java加密与解密的艺术
     *                   华章IT 15周年，《Java加密与解密的艺术》互动网首发http://www.china-pub.com/196506
     */
    public static String encodeRipeMD128Hex(byte[] data) throws Exception {
        //执行消息摘要
        byte[] b = encodeRipeMD128(data);
        // 做十六进制编码处理
        return new String(Hex.encode(b));
    }


    /**
     * RipeMD160消息摘要
     *
     * @param data 待做消息摘要处理的数据
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeRipeMD160(byte[] data) throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("RipeMD160");
        // 执行消息摘要
        return md.digest(data);
    }

    /**
     * RipeMD160Hex消息摘要
     *
     * @param data 待做消息摘要处理的数据
     * @return String 消息摘要
     * @throws Exception
     */
    public static String encodeRipeMD160Hex(byte[] data) throws Exception {
        // 执行消息摘要
        byte[] b = encodeRipeMD160(data);
        // 做十六进制编码处理
        return new String(Hex.encode(b));
    }

    /**
     * RipeMD256消息摘要
     *
     * @param data 待做消息摘要处理的数据
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeRipeMD256(byte[] data) throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("RipeMD256");
        // 执行消息摘要
        return md.digest(data);
    }

    /**
     * RipeMD256Hex消息摘要
     * 第6章验证数据完整性—消息摘要算法　197
     * 华章IT 15周年，《Java加密与解密的艺术》互动网首发http://www.china-pub.com/196506
     *
     * @param data 待做消息摘要处理的数据
     * @return String 消息摘要
     * @throws Exception
     */
    public static String encodeRipeMD256Hex(byte[] data) throws Exception {
        // 执行消息摘要
        byte[] b = encodeRipeMD256(data);
        // 做十六进制编码处理
        return new String(Hex.encode(b));
    }

    /**
     * RipeMD320消息摘要
     *
     * @param data 待做消息摘要处理的数据
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeRipeMD320(byte[] data) throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("RipeMD320");
        // 执行消息摘要
        return md.digest(data);
    }

    /**
     * RipeMD320Hex消息摘要
     *
     * @param data 待做消息摘要处理的数据
     * @return String 消息摘要
     * @throws Exception
     */
    public static String encodeRipeMD320Hex(byte[] data) throws Exception {
        // 执行消息摘要
        byte[] b = encodeRipeMD320(data);
        // 做十六进制编码处理
        return new String(Hex.encode(b));
    }

    /**
     * 测试RipeMD128
     * @throws Exception
     */
    @Test
    public final void testEncodeRipeMD128() throws Exception {
        String str = "RipeMD128消息摘要";
        // 获得摘要信息
        byte[] data1 = RipeMDCoder.encodeRipeMD128(str.getBytes());
        byte[] data2 = RipeMDCoder.encodeRipeMD128(str.getBytes());
        // 校验
        assertArrayEquals(data1, data2);
    }

    /**
     * 测试RipeMD128Hex
     * @throws Exception
     */
    @Test
    public final void testEncodeRipeMD128Hex() throws Exception {
        String str = "RipeMD128Hex消息摘要";
        // 获得摘要信息
        String data1 = RipeMDCoder.encodeRipeMD128Hex(str.getBytes());
        String data2 = RipeMDCoder.encodeRipeMD128Hex(str.getBytes());
        System.err.println("原文：\t" + str);
        System.err.println("RipeMD128Hex-1：\t" + data1);
        System.err.println("RipeMD128Hex-2：\t" + data2);
        // 校验
        assertEquals(data1, data2);
    }

    /**
     * 测试RipeMD160
     * @throws Exception
     */
    @Test
    public final void testEncodeRipeMD160() throws Exception {
        String str = "RipeMD160消息摘要";
        // 获得摘要信息
        byte[] data1 = RipeMDCoder.encodeRipeMD160(str.getBytes());
        byte[] data2 = RipeMDCoder.encodeRipeMD160(str.getBytes());
        // 校验
        assertArrayEquals(data1, data2);
    }

    /**
     * 测试RipeMD160Hex
     * @throws Exception
     */
    @Test
    public final void testEncodeRipeMD160Hex() throws Exception {
        String str = "RipeMD160Hex消息摘要";
        // 获得摘要信息
        String data1 = RipeMDCoder.encodeRipeMD160Hex(str.getBytes());
        String data2 = RipeMDCoder.encodeRipeMD160Hex(str.getBytes());
        System.err.println("原文：\t" + str);
        System.err.println("RipeMD160Hex-1：\t" + data1);
        System.err.println("RipeMD160Hex-2：\t" + data2);
        // 校验
        assertEquals(data1, data2);
    }

    /**
     * 测试RipeMD256
     * @throws Exception
     */
    @Test
    public final void testEncodeRipeMD256() throws Exception {
        String str = "RipeMD256消息摘要";
        // 获得摘要信息
        byte[] data1 = RipeMDCoder.encodeRipeMD256(str.getBytes());
        byte[] data2 = RipeMDCoder.encodeRipeMD256(str.getBytes());
        // 校验
        assertArrayEquals(data1, data2);
    }

    /**
     * 测试RipeMD256Hex
     * @throws Exception
     */
    @Test
    public final void testEncodeRipeMD256Hex() throws Exception {
        String str = "RipeMD256Hex消息摘要";
        // 获得摘要信息
        String data1 = RipeMDCoder.encodeRipeMD256Hex(str.getBytes());
        String data2 = RipeMDCoder.encodeRipeMD256Hex(str.getBytes());
        System.err.println("原文：\t" + str);
        System.err.println("RipeMD256Hex-1：\t" + data1);
        System.err.println("RipeMD256Hex-2：\t" + data2);
        // 校验
        assertEquals(data1, data2);
    }

    /**
     * 测试RipeMD320
     * @throws Exception
     */
    @Test
    public final void testEncodeRipeMD320() throws Exception {
        String str = "RipeMD320消息摘要";
        // 获得摘要信息
        byte[] data1 = RipeMDCoder.encodeRipeMD320(str.getBytes());
        byte[] data2 = RipeMDCoder.encodeRipeMD320(str.getBytes());
        // 校验
        assertArrayEquals(data1, data2);
    }

    /**
     * 测试RipeMD320Hex
     * @throws Exception
     */
    @Test
    public final void testEncodeRipeMD320Hex() throws Exception {
        String str = "RipeMD320Hex消息摘要";
        // 获得摘要信息
        String data1 = RipeMDCoder.encodeRipeMD320Hex(str.getBytes());
        String data2 = RipeMDCoder.encodeRipeMD320Hex(str.getBytes());
        System.err.println("原文：\t" + str);
        System.err.println("RipeMD320Hex-1：\t" + data1);
        System.err.println("RipeMD320Hex-2：\t" + data2);
        // 校验
        assertEquals(data1, data2);
    }

}
