import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

public class UrlEcrypWrapper {
	
	
	public String callUrlEcryp(String url) {
		
		String pubModulus = "30819F300D06092A864886F70D010101050003818D0030818902818100830BA2E464DAC4D898826FE1FFAA74B9B41899EBB703C8A9B4DF33CD8165F44F6CC3F9BE9CD1F8E4DA583CBFD09C886B023C4692BA998F4D847B0DA763C433E025CA5767B04FC3ED412BA6EAAAA56299F7F81179D2F77CA32770CAC6EB4D10A517E196F9834F99078A72E65818E89527ADDCCB8608D225BBE0407A781EF5B7550203010001";
//		String pubModulus = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDC6LkZNrE2JiCb+H/qnS5tBiZ67cDyKm03zPNgWX0T2zD+b6c0fjk2lg8v9CciGsCPEaSupmPTYR7DadjxDPgJcpXZ7BPw+1BK6bqqqVimff4EXnS93yjJ3DKxutNEKUX4Zb5g0+ZB4py5lgY6JUnrdzLhgjSJbvgQHp4HvW3VQIDAQAB";
		String pubExponent = "10001";
		
//		String priModulus = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIMLouRk2sTYmIJv4f+qdLm0GJnrtwPIqbTfM82BZfRPbMP5vpzR+OTaWDy/0JyIawI8RpK6mY9NhHsNp2PEM+AlyldnsE/D7UErpuqqpWKZ9/gRedL3fKMncMrG600QpRfhlvmDT5kHinLmWBjolSet3MuGCNIlu+BAenge9bdVAgMBAAECgYAOy/mQoHKzV2GhGNED9VNIxLUibCM2IipHagyHX90LQQye9Ew5mNvpu13LPj1n719l2Th+yOMd8zrKSjhkY3sX4k4IoFBEb9D/O3aEppqzab8xyfHUVqX5LgpZuHS2ez1H775Cg2NXNX0WAlGYQkGHazQjGlZsKwhBUN1OSU1LAQJBANYijBRu4FL6xtB8OD2JS+44DOqHOd57KOUZ5Y2eCca1ST9cS+oTcgP1ESe+LWJKUkQu48FHjWsrk+Z6iRIkE2kCQQCcqnXdU4l0FI1MTLvvfA2pKy2Iy1ukImrmDquPtEDMtmidCIHQYkFG9q3fjIvpCFqlVfSNWdAp8ZDy0TD3YoMNAkBNkjRGjqfqx3fQPL+u4xbDlEBxkU0QYHM/EzOKKs8F0dRYBJpnkGXEMznkuMr+CUhl9MqC0OXVBfNNEeSn0rPJAkAEH6R8j99VU9koQfjMY+qEImPPbvLZjiJcLdSsMqVl/gMg5wFrt7paINIQyUVBe/vO0EVglxWYlQgorALq8AfdAkEAnW1XewY2GiVXSI3pGG1otpowpqVfSwZrn9nn6YcWFndhgqDzNsjUNVGIPTEmzGWwZa9axiF8JKJtNSksIWVoCg==";
//		String priExponent = "10001";
		
		String encText = callRSAEnc_modulus(url, pubModulus, pubExponent);
		
		
		return encText;
	}

	private String callRSAEnc_modulus(String plainText, String pubModulus, String pubExponent) {
		BigInteger modulus = new BigInteger(pubModulus, 16);
		BigInteger pubexpo = new BigInteger(pubExponent, 16);
		
		PublicKey pub = generateRsaPublicKey(modulus, pubexpo);
		
		String sEncHex = new String();
		
		try {
			byte[] bOri = plainText.getBytes("UTF-8");
			sEncHex = byteArrayToHexString(rsaEncrypt(bOri, pub));
		} catch (Exception e) {
			e.toString();
		}
		
		
		return sEncHex;
	}

	private String byteArrayToHexString(byte[] bytes) {
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i<bytes.length; i++) {
			if(((int)bytes[i] & 0xff) < 0x10) {
				buffer.append("0");
			}
			buffer.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buffer.toString();
	}

	private byte[] rsaEncrypt(byte[] bOri, PublicKey pub) {
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, pub);
			return cipher.doFinal(bOri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private PublicKey generateRsaPublicKey(BigInteger modulus, BigInteger pubexpo) {
		try {
			return KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(modulus, pubexpo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		UrlEcrypWrapper urlEncypt = new UrlEcrypWrapper();
		String url = "안녕하세요";
		
		String encData = urlEncypt.callUrlEcryp(url);
		
		System.out.println(encData);
	}
	
}
