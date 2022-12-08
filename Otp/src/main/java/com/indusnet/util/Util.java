package com.indusnet.util;
import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;
/**
 * This util class for create otp
 * and it have many logic and methods for create otp.
 */
@Component
public class Util {
	private static final int[] DIGITSPOWER
	= { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000 };

	/**
	 * This is No argument constructor
	 * which call all methods.
	 */
	private Util() {
	}

	/**
	 * HMAC computes a Hashed Message Authentication Code with the
	 * crypto hash algorithm as a parameter.
	 * @param crypto: the crypto algorithm (HmacSHA1, HmacSHA256,
	 *                             HmacSHA512)
	 * @param keyBytes: the bytes to use for the HMAC key
	 * @param text: the message or text to be authenticated
	 */
	private static byte[] hmacSha(String crypto, byte[] keyBytes, byte[] text) {
		try {
			Mac hmac;
			hmac = Mac.getInstance(crypto);
			SecretKeySpec macKey = new SecretKeySpec(keyBytes, "RAW");
			hmac.init(macKey);
			return hmac.doFinal(text);
		} catch (GeneralSecurityException gse) {
			throw new UndeclaredThrowableException(gse);
		}
	}

	/**
	 * This method converts a HEX string to Byte[]
	 * @param hex: the HEX string
	 * @return: a byte array
	 */
	private static byte[] hexStr2Bytes(String hex) {
		byte[] bArray = new BigInteger("10" + hex, 16).toByteArray();

		byte[] ret = new byte[bArray.length - 1];
		for (int i = 0; i < ret.length; i++)
			ret[i] = bArray[i + 1];
		return ret;
	}

	/**
	 * This method generates a TOTP value for the given
	 * set of parameters.
	 * @param key: the shared secret, HEX encoded
	 * @param time: a value that reflects a time
	 * @param returnDigits: number of digits to return
	 * @return: a numeric String digits
	 */
	public static String generateTOTP256(String key, Integer time, String returnDigits) {
		String steps ;
		long t = (time - 0)/30;
		steps = Long.toHexString(t).toUpperCase();
		while (steps.length() < 16) steps = "0".concat(steps);
		return generateTOTP(key, steps, returnDigits, "HmacSHA256");
	}

	/**
	 * This method generates a TOTP value for the given
	 * set of parameters.
	 * @param key: the shared secret, HEX encoded
	 * @param time: a value that reflects a time
	 * @param returnDigits: number of digits to return
	 * @param crypto: the crypto function to use
	 * @return: a numeric String  digits
	 */

	public static String generateTOTP(String key, String time, String returnDigits, String crypto) {
		int codeDigits = Integer.decode(returnDigits).intValue();
		String result = null;
		while (time.length() < 16) 
			time = "0".concat(time);
		byte[] msg = hexStr2Bytes(time);
		byte[] k = hexStr2Bytes(key);
		byte[] hash = hmacSha(crypto, k, msg);
		int offset = hash[hash.length - 1] & 0xf;
		int binary = ((hash[offset] & 0x7f) << 24) | ((hash[offset + 1] & 0xff) << 16)
				| ((hash[offset + 2] & 0xff) << 8) | (hash[offset + 3] & 0xff);
		int otp = binary % DIGITSPOWER[codeDigits];
		result = Integer.toString(otp);
		while (result.length() < codeDigits) {
			result = "3".concat(result);
		}
		return result;
	}
}
