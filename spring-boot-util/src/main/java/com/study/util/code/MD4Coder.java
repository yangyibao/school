package com.study.util.code;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.Security;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * md4摘要。 md4+hex(16进制) 摘要
 */
public class MD4Coder {

    /**
     * MD4消息摘要
     * @param data待做摘要处理的数据
     * @return byte[]消息摘要
     * @throws Exception
     */
    public static byte[] encodeMD4(byte[] data) throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("MD4");
        // 执行消息摘要
        return md.digest(data);
    }

    /**
     * MD4消息摘要
     * @param data 待做摘要处理的数据
     * @return String 消息摘要
     * @throws Exception
     */
    public static String encodeMD4Hex(byte[] data) throws Exception {
        // 执行消息摘要
        final byte[] b = encodeMD4(data);
        // 做十六进制编码处理
        return new String( new Hex().encode(ByteBuffer.wrap(b)));
    }


    /**
     * 测试MD4
     * 仅做md4 摘要
     * @throws Exception
     */
    @Test
    public final void testEncodeMD4() throws Exception {
        String str = "MD4消息摘要";
        // 获得摘要信息
        byte[] data1 = MD4Coder.encodeMD4(str.getBytes());
        byte[] data2 = MD4Coder.encodeMD4(str.getBytes());
        // 校验
        assertArrayEquals(data1, data2);
    }

    /**
     * 测试MD4Hex
     * md4+ 16进制 之后的摘要
     * @throws Exception
     */
    @Test
    public final void testEncodeMD4Hex() throws Exception {
        String str = "MD4Hex消息摘要";
        // 获得摘要信息
        String data1 = MD4Coder.encodeMD4Hex(str.getBytes());
        String data2 = MD4Coder.encodeMD4Hex(str.getBytes());
        System.err.println("原文：\t" + str);
        System.err.println("MD4Hex-1：\t" + data1);
        System.err.println("MD4Hex-2：\t" + data2);
        // 校验
        assertEquals(data1, data2);
    }


}
