package cn.hash.sha.impl;

import cn.hash.sha.SHA5;

public class SHA512 extends SHA5 {

    private static final long[] INITIAL_HASHES = {
            0x6a09e667f3bcc908L, 0xbb67ae8584caa73bL,
            0x3c6ef372fe94f82bL, 0xa54ff53a5f1d36f1L,
            0x510e527fade682d1L, 0x9b05688c2b3e6c1fL,
            0x1f83d9abfb41bd6bL, 0x5be0cd19137e2179L
    };

    public SHA512() {
        super("SHA-512", 64, INITIAL_HASHES);
    }
}
