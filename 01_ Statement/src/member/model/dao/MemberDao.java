package member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import member.model.vo.Member;

public class MemberDao {

	public int insertMember(Member m) {
		// jdbc 사용
		
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		String query = "INSERT INTO MEMBER "
					 + "(MEMBERID,PASSWORD,MEMBERNAME,GENDER,AGE,EMAIL,PHONE,ADDRESS,HOBBY,ENROLLDATE)"
					 + "VALUES("
					 + "'"+m.getMemberid()+"',"
					 + "'"+m.getPassword()+"',"
					 + "'"+m.getMembername()+"',"
					 + "'"+m.getGender()+"',"
					 + 		m.getAge()+","
					 + "'"+m.getEmail()+"',"
					 + "'"+m.getPhone()+"',"
					 + "'"+m.getAddress()+"',"
					 + "'"+m.getHobby()+"',"
					 +"sysdate"
					 + ")";
		System.out.println("query@insertMember="+query);
		
		
		try {
			// 1.jdbc driver클래스 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");
			
			// 2.Connection 객체를 통해 DB연결
			// getConnection(String url,String user, String password)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", 
												"student", "student");
			System.out.println("Connection 객체 생성 성공");
			
			// 3.쿼리문을 실행할 Statement객체 생성
			stmt = conn.createStatement();
			
			// 4.쿼리문 전송, 실행결과 리턴받기
			// DML - executeUpdate(q)
			// DQL - executeQuery(q)
			result = stmt.executeUpdate(query);
			
			// 트랜잭션 관리
			if(result > 0) conn.commit();
			else conn.rollback();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			try {
				// 5. 자원반납 (역순으로)
				stmt.close();
				conn.close();
			} catch(SQLException e) {
			e.printStackTrace();
			}
		}
	
		return result;
	}

	public List<Member> selectAll() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<Member> list = null;
		
		
		try {
			String query = "select * from member";
			
			//1.jdbc driver클래스 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.Connection객체를 통해 db연결하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", 
					"student", "student");
			
			//3.쿼리문을 실행할 statement객체 생성
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<>();
			
			// 받은 결과값들을 Member, ArrayList객체에 차례로 담긴다.
			while(rset.next()) {
				//현재 ResultSet의 커서가 가리키는 행의 데이터에 접근
				Member m = new Member();
				m.setMemberid(rset.getString("memberid"));
				m.setPassword(rset.getString("password"));
				m.setMembername(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrolldate(rset.getDate("enrolldate"));
				
				list.add(m);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		finally {
			try {
				// 5. 자원반납 (역순으로)
				stmt.close();
				conn.close();
			} catch(SQLException e) {
			e.printStackTrace();
			}
		}
		
		return list;
	}

	public Member selectOne(String memberId) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Member m = null;
		
		String query = "select * from member where memberid ='"+memberId+"'";
		
		try {
			//1.jdbc driver클래스 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.Connection객체를 통해 db연결하기(127.0.0.1 == localhost)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", 
					"student", "student");
			
			//3.쿼리문을 실행할 statement객체 생성
			stmt = conn.createStatement();
			
			//4.쿼리문 전송, 실행 결과 리턴받기
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				m = new Member();
				m.setMemberid(rset.getString("memberid"));
				m.setPassword(rset.getString("password"));
				m.setMembername(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrolldate(rset.getDate("enrolldate"));
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				// 5. 자원반납 (역순으로)
				stmt.close();
				conn.close();
			} catch(SQLException e) {
			e.printStackTrace();
			}
		}
		
		
		return m;
	}

	public int updateMember(Member m) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String query = "update member set "
					 + "password='"+m.getPassword()+"',"
					 + "email='"+m.getEmail()+"',"
					 + "phone='"+m.getPhone()+"',"
					 + "address='"+m.getAddress()+"'"
					 + "where memberid ='"+m.getMemberid()+"'";
		System.out.println("query@dao.updatemember="+query);
		
		try {
			//1.jdbc driver클래스 등록(jdbc4.0미만의 legacy코드용)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.Connection객체를 통해 db연결하기(127.0.0.1 == localhost)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", 
					"student", "student");
			
			//3.쿼리문을 실행할 statement객체 생성
			stmt = conn.createStatement();
			
			//4.쿼리문 전송, 실행 결과 리턴받기
			result = stmt.executeUpdate(query);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				// 5. 자원반납 (역순으로)
				stmt.close();
				conn.close();
			} catch(SQLException e) {
			e.printStackTrace();
			}
		}
		
		return result;
	}

	public int deleteMember(String memberId) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String query = "delete from member where memberid = '"+memberId+"'";
		System.out.println("query@dao.deleteMember="+query);
		
		try {
			//1.jdbc driver클래스 등록(jdbc4.0미만의 legacy코드용)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.Connection객체를 통해 db연결하기(127.0.0.1 == localhost)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", 
					"student", "student");
			
			//3.쿼리문을 실행할 statement객체 생성
			stmt = conn.createStatement();
			
			//4.쿼리문 전송, 실행 결과 리턴받기
			result = stmt.executeUpdate(query);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				// 5. 자원반납 (역순으로)
				stmt.close();
				conn.close();
			} catch(SQLException e) {
			e.printStackTrace();
			}
		}
		
		return result;
	}

	public List<Member> selectName(String memberName) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<Member> list = null;
		
		
		try {
			String query = "select * from member where memberName like '"+memberName+"%'";
			
			//1.jdbc driver클래스 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.Connection객체를 통해 db연결하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", 
					"student", "student");
			
			//3.쿼리문을 실행할 statement객체 생성
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<>();
			
			// 받은 결과값들을 Member, ArrayList객체에 차례로 담긴다.
			while(rset.next()) {
				//현재 ResultSet의 커서가 가리키는 행의 데이터에 접근
				Member m = new Member();
				m.setMemberid(rset.getString("memberid"));
				m.setPassword(rset.getString("password"));
				m.setMembername(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrolldate(rset.getDate("enrolldate"));
				
				list.add(m);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		finally {
			try {
				// 5. 자원반납 (역순으로)
				stmt.close();
				conn.close();
			} catch(SQLException e) {
			e.printStackTrace();
			}
		}
		
		return list;
	}

}
