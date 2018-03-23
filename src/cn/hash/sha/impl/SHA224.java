package cn.hash.sha.impl;

import cn.hash.sha.SHA2;

public class SHA224 extends SHA2 {

    private static final int[] INITIAL_HASHES = {
            0xc1059ed8, 0x367cd507, 0x3070dd17, 0xf70e5939,
            0xffc00b31, 0x68581511, 0x64f98fa7, 0xbefa4fa4
    };

    public SHA224() {
        super("SHA-224", 28, INITIAL_HASHES);
    }
}
