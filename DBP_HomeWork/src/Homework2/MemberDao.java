package Homework2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Homework2.MemberDao;
import Homework2.MemberDto;


public class MemberDao {

	public static final int MEMBER_NONEXISTENT  = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;

	private static MemberDao instance = new MemberDao();

	private MemberDao() {
	}

	public static MemberDao getInstance(){
		return instance;
	}

	public int insertMember(MemberDto dto) {
		int ri = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "insert into meminfo values (?,?,?,?,?,?,?,?,?)";

		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getTel());
			pstmt.setString(5, dto.getDept());
			pstmt.setString(6, dto.getSex());
			pstmt.setString(7, dto.getBirth());
			pstmt.setString(8, dto.getIntroduction());
			pstmt.setString(9, dto.getEmail());
			pstmt.executeUpdate();
			ri = MemberDao.MEMBER_JOIN_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return ri;
	}

	public int confirmId(String id) {
		int ri = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select id from meminfo where id = ?";

		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next()){
				ri = MemberDao.MEMBER_EXISTENT;
			} else {
				ri = MemberDao.MEMBER_NONEXISTENT;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return ri;
	}

	public int userCheck( String id, String pw) {
		int ri = 0;
		String dbPw;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select pwd from meminfo where id = ?";

		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();

			if(set.next()) {
				dbPw = set.getString("pwd");
				if(dbPw.equals(pw)) {
					ri = MemberDao.MEMBER_LOGIN_SUCCESS;				// 연결 Ok
				} else {
					ri = MemberDao.MEMBER_LOGIN_PW_NO_GOOD;		// 연결 X
				}
			} else {
				ri = MemberDao.MEMBER_LOGIN_IS_NOT;		// X	
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}

	public MemberDto getMember(String id) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from meminfo where id = ?";
		MemberDto dto = null;

		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				dto = new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setDept(rs.getString("dept"));
				dto.setTel(rs.getString("tel"));
				dto.setIntroduction(rs.getString("introduction"));
				dto.setSex(rs.getString("sex"));
				dto.setBirth(rs.getString("birth"));
				;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dto;

	}
	

	public int updateMember(MemberDto dto) {
		int ri = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "update meminfo set pwd=?, email=?, tel=?, dept=?, sex=?, introduction=? , birth=? where id=?";

		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getTel());
			pstmt.setString(4, dto.getDept());
			pstmt.setString(5, dto.getSex());
			pstmt.setString(6, dto.getIntroduction());
			pstmt.setString(7, dto.getBirth());
			pstmt.setString(8, dto.getId());
			ri = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return ri;
	}

	private Connection getConnection() {

		Context context = null;
		DataSource dataSource = null;
		Connection connection = null;
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/mysql");
			connection = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}
	
	public  ArrayList<MemberDto> show(Statement st) throws SQLException {
		String sql = "select * from meminfo";
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		ResultSet rs = st.executeQuery(sql);
		List<MemberDto>data = new ArrayList<MemberDto>();
		int cnt = 0;
		
		while(rs.next()) {
			MemberDto mem = new MemberDto();
			
			String id = rs.getString("id");
			String name = rs.getString("name");
			String tel = rs.getString("tel");
			String email = rs.getString("email");
			String dept = rs.getString("dept");
			String sex = rs.getString("sex");
			String birth = rs.getString("birth");
			String introduction = rs.getString("introduction");
			
			mem.setId(id); 
			mem.setName(name);
			mem.setTel(tel);
			mem.setEmail(email);
			mem.setDept(dept);
			mem.setSex(sex);
			mem.setBirth(birth);
			mem.setIntroduction(introduction);
			
			System.out.printf("id:  %d, name: %s, tel: %s, email: %s, dept: %s, gender: %s, birth: %s, intro: %s "
					+ "\n", id, name, tel, email, dept,sex ,birth ,introduction);
			//data.add(mem);
			cnt++;
		}
		rs.close();
		return list;
	}

	
	
	public ArrayList<MemberDto> membersAll() {
		// TODO Auto-generated method stub
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from meminfo";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			System.out.println("============");
			while (rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setDept(rs.getString("dept"));
				dto.setSex(rs.getString("sex"));
				dto.setBirth(rs.getString("birth"));
				dto.setIntroduction(rs.getString("introduction"));
				dtos.add(dto);
			}
			System.out.println("--------------------------");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public ArrayList<MemberDto> deleteAll() {
		// TODO Auto-generated method stub
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "delete from meminfo";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			int ret = pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}

}