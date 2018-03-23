package cn.hash.sha.impl;

import cn.hash.sha.SHA5;

public class SHA512_256 extends SHA5 {

    private static final long[] INITIAL_HASHES = {
            0x22312194FC2BF72CL, 0x9F555FA3C84C64C2L,
            0x2393B86B6F53B151L, 0x963877195940EABDL,
            0x96283EE2A88EFFE3L, 0xBE5E1E2553863992L,
            0x2B0199FC2C85B8AAL, 0x0EB72DDC81C52CA2L
    };

    public SHA512_256() {
        super("SHA-512/256", 32, INITIAL_HASHES);
    }
}
