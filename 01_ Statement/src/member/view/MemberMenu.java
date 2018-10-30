package member.view;

import java.util.List;
import java.util.Scanner;

import member.controller.MemberController;
import member.model.vo.Member;

public class MemberMenu {
	
	Scanner sc = new Scanner(System.in);
	
	public void MainMenu() {
		
		String menu = "******* 회원 관리 프로그램 *******\n"
					+ "1. 회원 전체 조회\n"
					+ "2. 회원 아이디 조회\n"
					+ "3. 회원 이름으로 조회\n"
					+ "4. 회원 가입\n"
					+ "5. 회원 정보 변경\n"
					+ "6. 회원 탈퇴\n"
					+ "9. 프로그램 끝내기\n"
					+ "번호선택 > ";
		
		int choice;
		
		do {
			System.out.println(menu);
			choice = sc.nextInt();
			
			switch(choice){
			case 1: 
				new MemberController().selectAll();
				break;
			case 2: 
				new MemberController().selectOne(inputMemberId());
				break;
			case 3:
				new MemberController().selectName(inputMemberName());
				break;
			case 4: 
				Member m = inputMember();
				new MemberController().insetMember(m);
				break;
			case 5: 
				new MemberController().updateMember(updateMember());
				break;
			case 6: 
				new MemberController().deleteMember(inputMemberId());
				break;
			case 9: 
				System.out.println("정말로 끝내시겠습니다?(y/n) : ");
				if('y' == sc.next().toLowerCase().charAt(0))
					return;
				break;
			default:
				System.out.println("잘못 입력하셨다능");
			}
			
		}while(true);
	}

	private Member updateMember() {
		Member m = new Member();
		m.setMemberid(inputMemberId());
		
		System.out.print("암호 : ");
		m.setPassword(sc.next());
		System.out.print("이메일: ");
		m.setEmail(sc.next());
		System.out.print("전화번호(-빼고 입력) : ");
		m.setPhone(sc.next());
		System.out.print("주소  : ");
		sc.nextLine();
		m.setAddress(sc.nextLine());
		
		return m;
	}
	
	private String inputMemberId() {
		System.out.println("회원 아이디 입력 : ");
		return sc.next();
	}

	private String inputMemberName() {
		System.out.println("회원 이름 입력 : ");
		return sc.next();
	}
	
	private Member inputMember() {
		// TODO Auto-generated method stub
		Member m = new Member();
		System.out.println("새로운 회원정보를 입력하세요");
		System.out.print("아이디 : ");
		m.setMemberid(sc.next());
		
		System.out.print("패스워드 : ");
		m.setPassword(sc.next());
		
		System.out.print("이름 : ");
		m.setMembername(sc.next());
		
		System.out.print("나이 : ");
		m.setAge(sc.nextInt());
		
		System.out.print("성별(M/F) : ");
		m.setGender(sc.next().toUpperCase().charAt(0)+"");
		
		System.out.print("이메일 : ");
		m.setEmail(sc.next());
		
		System.out.print("전화번호(-빼고 입력) : ");
		m.setPhone(sc.next());
		
		System.out.print("주소 : ");
		sc.nextLine(); // 개행문자 날리기
		m.setAddress(sc.nextLine());
		
		System.out.print("취미(,를 통해 공백없이 나열) : ");
		m.setHobby(sc.next());
		
		return m;
	}

	public void displaySuccess(String message) {
		System.out.println("실행결과 : " +message);
		
	}

	public void displayError(String message) {
		System.out.println("실행결과 : " +message);	
	}

	public void displayMemberList(List<Member> list) {
		System.out.println("조회된 회원정보는 다음과 같습니다.");
		System.out.println("아이디\t패스워드\t이름\t성별\t나이\t이메일\t전화번호\t주소\t취미\t가입");
		System.out.println("-----------------------------------------------------------");
		
		for(Member m : list) {
			System.out.println(m);
		}
		
	}

	public void displayMember(Member m) {
		System.out.println("조회된 회원정보는 다음과 같습니다.");
		System.out.println("아이디\t패스워드\t이름\t성별\t나이\t이메일\t전화번호\t주소\t취미\t가입");
		System.out.println("-----------------------------------------------------------");
		
		System.out.println(m);
	}

}
