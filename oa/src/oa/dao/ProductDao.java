package oa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oa.vo.Product;
import oa.vo.ProductManual;
import oa.vo.ProductPic;

public class ProductDao {

	public List<Product> selectProduct() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //조회 결과를 참조하는 변수
		
		ArrayList<Product> prArray = new ArrayList<>(); // 조회 결과를 저장할 변수
		
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",// 연결 정보
					"admin", "060104"); //계정 정보
			
			//3. SQL 작성 + 명령 만들기
			String sql = 
					"SELECT productno, productname, price, regdate, sales, active " +
					"FROM product " +
					"ORDER BY productno DESC";
				
			pstmt = conn.prepareStatement(sql);
			
			//4. 명령 실행
			rs = pstmt.executeQuery(); // select를 위한 메서드
			
			//5. 결과가 있다면 결과 처리 (select)
			while (rs.next()) { //조회된 데이터가 있다면
				//조회된 데이터를 upload 객체에 저장
				Product pr = new Product();
				pr.setProductNo(rs.getInt(1));
				pr.setProductName(rs.getString(2));
				pr.setPrice(rs.getInt(3));
				pr.setRegDate(rs.getDate(4));
				pr.setSales(rs.getInt(5));
				pr.setActive(rs.getBoolean(6));
				
				prArray.add(pr); // 한 건의 데이터를 데이터 목록에 추가
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}		
		return prArray;
	}

	public int insertProduct(Product pr) {

		Connection conn = null;
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		int newUploadNo = -1;
		
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",// 연결 정보
					"admin", "060104"); //계정 정보
			
			//3. SQL 작성 + 명령 만들기
			String sql = 
					"INSERT INTO PRODUCT (productno, productname, price, content) " +
					"VALUES (PRODUCT_SEQUENCE.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pr.getProductName());
			pstmt.setInt(2, pr.getPrice());
			pstmt.setString(3, pr.getContent());
			
			//4. 명령 실행
			pstmt.executeUpdate(); // insert, update, delete를 위한 메서드
			
			//4-1. INSERT 된 자료의 SEQUENCE.CURRVAL 가져오기 (SELECT)
			sql = "SELECT PRODUCT_SEQUENCE.CURRVAL FROM DUAL";
			pstmt2 = conn.prepareStatement(sql);
			rs = pstmt2.executeQuery();
			
			//5. 결과가 있다면 결과 처리 (select)
			rs.next();
			newUploadNo = rs.getInt(1);
			
		} catch (Exception ex) {
			
		} finally {
			//6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { pstmt2.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return newUploadNo;
	}

	public void insertProductManual(ProductManual manual) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",// 연결 정보
					"admin", "060104"); //계정 정보
			
			//3. SQL 작성 + 명령 만들기
			String sql = 
					"INSERT INTO PRODUCTMANUAL (manualno, productno, savedmanualname, usermanualname) " +
					"VALUES (PRODUCTMANUAL_SEQUENCE.NEXTVAL, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, manual.getProductNo());
			pstmt.setString(2, manual.getSavedManualName());
			pstmt.setString(3, manual.getUserManualName());
			
			//4. 명령 실행
			pstmt.executeUpdate(); // insert, update, delete를 위한 메서드
			
			//5. 결과가 있다면 결과 처리 (select)
		} catch (Exception ex) {
			
		} finally {
			//6. 연결 종료
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}		
	}

	public void insertProductPic(ProductPic pic) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",// 연결 정보
					"admin", "060104"); //계정 정보
			
			//3. SQL 작성 + 명령 만들기
			String sql = 
					"INSERT INTO PRODUCTPIC (picno, productno, savedpicname, userpicname) " +
					"VALUES (PRODUCTPIC_SEQUENCE.NEXTVAL, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pic.getProductNo());
			pstmt.setString(2, pic.getSavedPicName());
			pstmt.setString(3, pic.getUserPicName());
			
			//4. 명령 실행
			pstmt.executeUpdate(); // insert, update, delete를 위한 메서드
			
			//5. 결과가 있다면 결과 처리 (select)
		} catch (Exception ex) {
			
		} finally {
			//6. 연결 종료
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}		
	}

	public Product selectProductByPrNo(int prNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //조회 결과를 참조하는 변수
		
		Product pr = null; // 조회 결과를 저장할 변수
		
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",// 연결 정보
					"admin", "060104"); //계정 정보
			
			//3. SQL 작성 + 명령 만들기
			String sql = 
					"SELECT productno, productname, price, regdate, content, sales, active " +
					"FROM product " +
					"WHERE productno = ? AND active = '1'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prNo);
			
			//4. 명령 실행
			rs = pstmt.executeQuery(); // select를 위한 메서드
			
			//5. 결과가 있다면 결과 처리 (select)
			if (rs.next()) { //조회된 데이터가 있다면
				//조회된 데이터를 upload 객체에 저장
				pr = new Product();
				pr.setProductNo(rs.getInt(1));
				pr.setProductName(rs.getString(2));
				pr.setPrice(rs.getInt(3));
				pr.setRegDate(rs.getDate(4));
				pr.setContent(rs.getString(5));
				pr.setSales(rs.getInt(6));
				pr.setActive(rs.getBoolean(7));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return pr; //조회된 데이터를 저장항 List 객체 반환
	}

	public List<ProductManual> selectManualByPrNo(int prNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //조회 결과를 참조하는 변수
		
		ArrayList<ProductManual> manuals = new ArrayList<>(); // 조회 결과를 저장할 변수
		
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",// 연결 정보
					"admin", "060104"); //계정 정보
			
			//3. SQL 작성 + 명령 만들기
			String sql = 
					"SELECT manualno, productno, savedmanualname, usermanualname, active " +
					"FROM productmanual " +
					"WHERE productno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prNo);
			
			//4. 명령 실행
			rs = pstmt.executeQuery(); // select를 위한 메서드
			
			//5. 결과가 있다면 결과 처리 (select)
			while (rs.next()) { //조회된 데이터가 있다면
				//조회된 데이터를 UploadFile 객체에 저장
				ProductManual manual = new ProductManual();
				manual.setManualNo(rs.getInt(1));
				manual.setProductNo(rs.getInt(2));
				manual.setSavedManualName(rs.getString(3));
				manual.setUserManualName(rs.getString(4));
				manual.setActive(rs.getBoolean(5));
				//한 건의 데이터를 목록에 추가
				manuals.add(manual);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return manuals; //조회된 데이터를 저장항 List 객체 반환
	}

	public List<ProductPic> selectPicByPrNo(int prNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //조회 결과를 참조하는 변수
		
		ArrayList<ProductPic> pics = new ArrayList<>(); // 조회 결과를 저장할 변수
		
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",// 연결 정보
					"admin", "060104"); //계정 정보
			
			//3. SQL 작성 + 명령 만들기
			String sql = 
					"SELECT picno, productno, savedpicname, userpicname, active " +
					"FROM productpic " +
					"WHERE productno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prNo);
			
			//4. 명령 실행
			rs = pstmt.executeQuery(); // select를 위한 메서드
			
			//5. 결과가 있다면 결과 처리 (select)
			while (rs.next()) { //조회된 데이터가 있다면
				//조회된 데이터를 UploadFile 객체에 저장
				ProductPic pic = new ProductPic();
				pic.setPicNo(rs.getInt(1));
				pic.setProductNo(rs.getInt(2));
				pic.setSavedPicName(rs.getString(3));
				pic.setUserPicName(rs.getString(4));
				pic.setActive(rs.getBoolean(5));
				//한 건의 데이터를 목록에 추가
				pics.add(pic);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return pics; //조회된 데이터를 저장항 List 객체 반환
	}

	public void deleteProductByPrNo(int prNo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",// 연결 정보
					"admin", "060104"); //계정 정보
			
			//3. SQL 작성 + 명령 만들기
			String sql = 
					"UPDATE product " +
					"SET active = '0' " +
					"WHERE productno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prNo);
			
			//4. 명령 실행
			pstmt.executeUpdate(); // insert, update, delete를 위한 메서드
			
			//5. 결과가 있다면 결과 처리 (select)
		} catch (Exception ex) {
			
		} finally {
			//6. 연결 종료
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}


	public void deleteManualByManualNo(int manualNo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",// 연결 정보
					"admin", "060104"); //계정 정보
			
			//3. SQL 작성 + 명령 만들기
			String sql = 
					"UPDATE PRODUCTMANUAL " +
					"SET active = '0' " +
					"WHERE manualno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, manualNo);
			
			//4. 명령 실행
			pstmt.executeUpdate(); // insert, update, delete를 위한 메서드
			
			//5. 결과가 있다면 결과 처리 (select)
		} catch (Exception ex) {
			
		} finally {
			//6. 연결 종료
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}

	public void deletePicByPicNo(int picNo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",// 연결 정보
					"admin", "060104"); //계정 정보
			
			//3. SQL 작성 + 명령 만들기
			String sql = 
					"UPDATE PRODUCTPIC " +
					"SET active = '0' " +
					"WHERE picno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, picNo);
			
			//4. 명령 실행
			pstmt.executeUpdate(); // insert, update, delete를 위한 메서드
			
			//5. 결과가 있다면 결과 처리 (select)
		} catch (Exception ex) {
			
		} finally {
			//6. 연결 종료
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}
	
	public void updateProduct(Product pr) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",// 연결 정보
					"admin", "060104"); //계정 정보
			
			//3. SQL 작성 + 명령 만들기
			String sql = 
					"UPDATE product " +
					"SET productname = ?, price = ?, sales = ?, content = ? " +
					"WHERE productno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pr.getProductName());
			pstmt.setInt(2, pr.getPrice());
			pstmt.setInt(3, pr.getSales());
			pstmt.setString(4, pr.getContent());
			pstmt.setInt(5, pr.getProductNo());
			
			//4. 명령 실행
			pstmt.executeUpdate(); // insert, update, delete를 위한 메서드
			
			//5. 결과가 있다면 결과 처리 (select)
		} catch (Exception ex) {
			
		} finally {
			//6. 연결 종료
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}

	public List<Product> searchByProductName(String word) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //조회 결과를 참조하는 변수
		
		ArrayList<Product> prArray = new ArrayList<>(); // 조회 결과를 저장할 변수
		
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",// 연결 정보
					"admin", "060104"); //계정 정보
			
			//3. SQL 작성 + 명령 만들기
			String sql = 
					"SELECT productno, productname, price, regdate, sales, active " +
					"FROM product " +
					"WHERE productname LIKE ? " +
					"ORDER BY productno DESC";
				
			pstmt = conn.prepareStatement(sql);
			String keyword = "%" + word + "%";
			pstmt.setString(1, keyword);
			
			//4. 명령 실행
			rs = pstmt.executeQuery(); // select를 위한 메서드
			
			//5. 결과가 있다면 결과 처리 (select)
			while (rs.next()) { //조회된 데이터가 있다면
				//조회된 데이터를 upload 객체에 저장
				Product pr = new Product();
				pr.setProductNo(rs.getInt(1));
				pr.setProductName(rs.getString(2));
				pr.setPrice(rs.getInt(3));
				pr.setRegDate(rs.getDate(4));
				pr.setSales(rs.getInt(5));
				pr.setActive(rs.getBoolean(6));
				
				prArray.add(pr); // 한 건의 데이터를 데이터 목록에 추가
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}		
		return prArray;
	}

	public List<Product> searchByProductContent(String word) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //조회 결과를 참조하는 변수
		
		ArrayList<Product> prArray = new ArrayList<>(); // 조회 결과를 저장할 변수
		
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",// 연결 정보
					"admin", "060104"); //계정 정보
			
			//3. SQL 작성 + 명령 만들기
			String sql = 
					"SELECT productno, productname, price, regdate, sales, active " +
					"FROM product " +
					"WHERE content LIKE ? " +
					"ORDER BY productno DESC";
				
			pstmt = conn.prepareStatement(sql);
			String keyword = "%" + word + "%";
			pstmt.setString(1, keyword);
			
			//4. 명령 실행
			rs = pstmt.executeQuery(); // select를 위한 메서드
			
			//5. 결과가 있다면 결과 처리 (select)
			while (rs.next()) { //조회된 데이터가 있다면
				//조회된 데이터를 upload 객체에 저장
				Product pr = new Product();
				pr.setProductNo(rs.getInt(1));
				pr.setProductName(rs.getString(2));
				pr.setPrice(rs.getInt(3));
				pr.setRegDate(rs.getDate(4));
				pr.setSales(rs.getInt(5));
				pr.setActive(rs.getBoolean(6));
				
				prArray.add(pr); // 한 건의 데이터를 데이터 목록에 추가
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}		
		return prArray;
	}

	
	
}
