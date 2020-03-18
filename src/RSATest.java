import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSATest {
    public static void main(String[] args) throws Exception {
        // RSA 키쌍을 생성합니다.
        KeyPair keyPair = CipherUtil.genRSAKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String plainText = "1aaaa231231암호화 할 문자열 입니다.";

        // Base64 인코딩된 암호화 문자열 입니다.
        String encrypted = CipherUtil.encryptRSA(plainText, publicKey);
        System.out.println("encrypted : " + encrypted);

        // 복호화 합니다.
        String decrypted = CipherUtil.decryptRSA(encrypted, privateKey);
        System.out.println("decrypted : " + decrypted);

        // 공개키를 Base64 인코딩한 문자일을 만듭니다.
        byte[] bytePublicKey = publicKey.getEncoded();
        String base64PublicKey = Base64.getEncoder().encodeToString(bytePublicKey);
        System.out.println("Base64 Public Key : " + base64PublicKey);

        // 개인키를 Base64 인코딩한 문자열을 만듭니다.
        byte[] bytePrivateKey = privateKey.getEncoded();
        String base64PrivateKey = Base64.getEncoder().encodeToString(bytePrivateKey);
        System.out.println("Base64 Private Key : " + base64PrivateKey);
    }
// encrypted : ZhMekeRIMWOczEQgj3YZKd91ckRrmKOgJYovNNdaiDiQCJWO4XWMP9itj/8wYA+tEGr7VaZFTRWX9KOeuNFXJM7XEFNzNdGJubeFy0FVu8zpZQUK/04/aIk1NATo4ZaFhbYhw2k4a8IR9TANkkjCZrkV4VkLsN9/VHYkEj3nXZc=
// decrypted : 1231231암호화 할 문자열 입니다.
// Base64 Public Key : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDC6LkZNrE2JiCb+H/qnS5tBiZ67cDyKm03zPNgWX0T2zD+b6c0fjk2lg8v9CciGsCPEaSupmPTYR7DadjxDPgJcpXZ7BPw+1BK6bqqqVimff4EXnS93yjJ3DKxutNEKUX4Zb5g0+ZB4py5lgY6JUnrdzLhgjSJbvgQHp4HvW3VQIDAQAB
// Base64 Private Key : MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIMLouRk2sTYmIJv4f+qdLm0GJnrtwPIqbTfM82BZfRPbMP5vpzR+OTaWDy/0JyIawI8RpK6mY9NhHsNp2PEM+AlyldnsE/D7UErpuqqpWKZ9/gRedL3fKMncMrG600QpRfhlvmDT5kHinLmWBjolSet3MuGCNIlu+BAenge9bdVAgMBAAECgYAOy/mQoHKzV2GhGNED9VNIxLUibCM2IipHagyHX90LQQye9Ew5mNvpu13LPj1n719l2Th+yOMd8zrKSjhkY3sX4k4IoFBEb9D/O3aEppqzab8xyfHUVqX5LgpZuHS2ez1H775Cg2NXNX0WAlGYQkGHazQjGlZsKwhBUN1OSU1LAQJBANYijBRu4FL6xtB8OD2JS+44DOqHOd57KOUZ5Y2eCca1ST9cS+oTcgP1ESe+LWJKUkQu48FHjWsrk+Z6iRIkE2kCQQCcqnXdU4l0FI1MTLvvfA2pKy2Iy1ukImrmDquPtEDMtmidCIHQYkFG9q3fjIvpCFqlVfSNWdAp8ZDy0TD3YoMNAkBNkjRGjqfqx3fQPL+u4xbDlEBxkU0QYHM/EzOKKs8F0dRYBJpnkGXEMznkuMr+CUhl9MqC0OXVBfNNEeSn0rPJAkAEH6R8j99VU9koQfjMY+qEImPPbvLZjiJcLdSsMqVl/gMg5wFrt7paINIQyUVBe/vO0EVglxWYlQgorALq8AfdAkEAnW1XewY2GiVXSI3pGG1otpowpqVfSwZrn9nn6YcWFndhgqDzNsjUNVGIPTEmzGWwZa9axiF8JKJtNSksIWVoCg==

//encrypted : NbA7Xml7Z7Rr3KpRGv4B0j0h8aVhtmYE++IE8LIKAhZA52P20sfyO+nJwAzr0f32RlsDCb9IeeNUPEqqLjgqhbKH6nNSR2tF4+m4EsK0kHCtLMrFUEcRMf3Gup4zM5oBBm8y9c8PwCVg/PoRW477kQOQFpOrSzLJDyGPUfFvS5M=
//decrypted : 1aaaa231231암호화 할 문자열 입니다.
//Base64 Public Key : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJQXK2ochmVdL43OtK4GazBE2Y+ZjuN8qmRsLLGt9B1Hc+lppqfNakWCuJKoh27bGDUDlaGsZtzsDZk0I9X6de4SlEt4tAc5xE/H5jfUrGBllzZB/eHALmqORd4rsWAjA8vu6+6gmtgo4spG7/C/5MVjOU3WsHldQuc+v+b+9iGQIDAQAB
//Base64 Private Key : MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMlBcrahyGZV0vjc60rgZrMETZj5mO43yqZGwssa30HUdz6Wmmp81qRYK4kqiHbtsYNQOVoaxm3OwNmTQj1fp17hKUS3i0BznET8fmN9SsYGWXNkH94cAuao5F3iuxYCMDy+7r7qCa2Cjiykbv8L/kxWM5TdaweV1C5z6/5v72IZAgMBAAECgYEAhHGOxYO4dyg5286/BZqh22OUkXyI2RRcR4q/vJvlmtPw2X6RBk07kBH6LJ2SCvon3OvZomOGfFMY9NaFPBCL+qi+aQCCBDZzGkDzWVNqnHRD3ml6cnucVCIIOMx60ytZL1EKNH9zyoaL9RZ70RsOkWjQRBQpIF/ERw0dtZKpGIECQQDpzBWqZId6WnfE4Lfrjw9JRkVj3YGk9M3gG28XatUi3W498/WLFhCTkhnNdIbA1sKd6LMZuk/3v8tUiHcKfHORAkEA3F48ZTBt1kg5zeXYsBImZTmUXBpUyLCKRbwc21qbeTvLxywCDYGkYwGt1ekZPYWpycHYb9qAU8dieq5VCq0yCQJBAMVpyFmKbUMJJNyfC23h7Hxo06rRnOjWtQgo4hMZ6EJ6St4RIyKATRruYM81N5uZO9fuZ1ywD1oZHu2QFURLdaECQHWextWrt7R78H55g+LWjS/SP+G5HC/SKrt14GEkR3+57tZsItxaqgc+Si62sAYqZamOkZuCIKSW83VPv4xnTSECQQCu5n5dCxFYyf81/ODcSAVAThXfZizZzCJ6wGmC2Tk989ZEnls4ielldjLTvktlpd5LGOpmXX5BOQGJMmFJvkxs

}

