package com.huiyi.huiyiproject;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

@SpringBootTest
class HuiyiProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	/**
	 * 临时生成可用的jwt秘钥
	 */
	@Test
	public void generateSecreKey(){
		// 使用 HS512 算法生成安全的密钥
		byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();

		// 将密钥编码为 Base64 字符串
		String base64Key = Base64.getEncoder().encodeToString(keyBytes);

		// 打印生成的安全密钥
		System.out.println("生成的安全密钥 (Base64 编码): " + base64Key);
	}

}
