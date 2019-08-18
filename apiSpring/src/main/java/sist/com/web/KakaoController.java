package sist.com.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class KakaoController {

	private kakao_restapi kakao_restapi = new kakao_restapi();

	@RequestMapping(value = "/oauth", produces = "application/json")
	public String kakaoLogin(@RequestParam("code") String code, Model model, HttpSession session) {
		System.out.println("�α��� �Ҷ� �ӽ� �ڵ尪");
		// īī�� Ȩ���������� ���� ��� �ڵ�
		System.out.println(code);
		System.out.println("�α��� �� �����");

		// īī�� rest api ��ü ����
		kakao_restapi kr = new kakao_restapi();
		// ������� node�� �����
		JsonNode node = kr.getAccessToken(code);
		// ����� ���
		System.out.println(node);
		// ��� �ȿ� �ִ� access_token���� ���� ���ڿ��� ��ȯ
		String token = node.get("access_token").toString();
		// ���ǿ� ����ش�.
		session.setAttribute("token", token);

		return "kakaoSuccess";
	}
}
