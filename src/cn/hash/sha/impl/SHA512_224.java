package cn.hash.sha.impl;

import cn.hash.sha.SHA5;

public class SHA512_224 extends SHA5 {

    private static final long[] INITIAL_HASHES = {
            0x8C3D37C819544DA2L, 0x73E1996689DCD4D6L,
            0x1DFAB7AE32FF9C82L, 0x679DD514582F9FCFL,
            0x0F6D2B697BD44DA8L, 0x77E36F7304C48942L,
            0x3F9D85A86A1D36C8L, 0x1112E6AD91D692A1L
    };

    public SHA512_224() {
        super("SHA-512/224", 28, INITIAL_HASHES);
    }
}
