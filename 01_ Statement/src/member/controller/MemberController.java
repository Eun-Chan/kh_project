package member.controller;

import java.util.List;

import member.model.dao.MemberDao;
import member.model.vo.Member;
import member.view.MemberMenu;

public class MemberController {

	public void insetMember(Member m) {
		System.out.println("m@MemberController.insertMember ="+m);
		int result = new MemberDao().insertMember(m); // 몇개의 행이추가됐는지 리턴됨
		System.out.println("result@MemberController.insertMember"+result);
		if(result > 0) {
			new MemberMenu().displaySuccess("회원가입 성공");
		}
		else
			new MemberMenu().displayError("회원가입 실패");
			
	}
	
	// 전체 회원 조회
	public void selectAll() {
		List<Member> list = new MemberDao().selectAll();
		//System.out.println("list@MemberoController.selectAll="+list);
		
		if(!list.isEmpty()) {
			new MemberMenu().displayMemberList(list);
		}
		else {
			new MemberMenu().displayError("데이터가 없슴둥");
		}
	}

	public void selectOne(String memberId) {
		Member m = new MemberDao().selectOne(memberId);
		System.out.println("m@MemberController.selectOne="+m);
		
		if(m != null) {
			new MemberMenu().displayMember(m);
		}
		else
			new MemberMenu().displayError("데이터가 없슴둥");
	}

	public void updateMember(Member m) {
		int result = new MemberDao().updateMember(m);
		System.out.println("result@MemberController.updateMember="+result);
		
		if(result > 0)
			new MemberMenu().displaySuccess("회원 정보 수정 성공!");
		else
			new MemberMenu().displayError("회원 정보 수정 실패");
	}

	public void deleteMember(String MemberId) {
		int result = new MemberDao().deleteMember(MemberId);
		System.out.println("result@MemberController.deleteMember="+result);	
		
		if(result > 0)
			new MemberMenu().displaySuccess("회원 정보 삭제 성공!");
		else
			new MemberMenu().displayError("회원 정보 삭제 실패");
	}

	public void selectName(String memberName) {
		List<Member> list = new MemberDao().selectName(memberName);
		//System.out.println("result@MemberController.selectName="+list);
		
		if(!list.isEmpty()) {
			new MemberMenu().displayMemberList(list);
		}
		else {
			new MemberMenu().displayError("데이터가 없슴둥");
		}
		
	}
}
