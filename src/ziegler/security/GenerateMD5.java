package ziegler.security;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

public class GenerateMD5 {

	public static void main(String [] args) throws NoSuchAlgorithmException{
		System.out.println(DigestUtils.md5Hex("I will graduate soon"));
	}
}