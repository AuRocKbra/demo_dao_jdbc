package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection conexao = null;
	
	public SellerDaoJDBC(Connection com) {
		this.conexao = com;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conexao.prepareStatement("SELECT seller.*,department.Name as DepName\n" + 
					"FROM seller INNER JOIN department\n" + 
					"ON seller.DepartmentId = department.Id\n" + 
					"WHERE seller.Id = ?");
			st.setInt(1, id);
			rs=st.executeQuery();
			if(rs.next()) {
				Department dp = instanciaDapartamento(rs);
				Seller obj = instanciaSeller(rs,dp);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}	

	private Seller instanciaSeller(ResultSet rs, Department dp) throws SQLException{
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dp);
		return obj;
	}

	private Department instanciaDapartamento(ResultSet rs) throws SQLException {
		Department dp = new Department();
		dp.setId(rs.getInt("DepartmentId"));
		dp.setNome(rs.getString("DepName"));
		return dp;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Seller>findByDepartment(Department department){
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conexao.prepareStatement("SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department " + 
					"ON seller.DepartmentId = department.Id " + 
					"WHERE DepartmentId = ? " + 
					"ORDER BY Name;");
			st.setInt(1, department.getId());
			rs=st.executeQuery();
			List<Seller> lista = new ArrayList<>();
			Map<Integer,Department> map = new HashMap<>();
			while(rs.next()) {
				if(map.get(rs.getInt("DepartmentId"))==null) {
					map.put(rs.getInt("DepartmentId"),instanciaDapartamento(rs));
				}
				Seller obj = instanciaSeller(rs,map.get(rs.getInt("DepartmentId")));
				lista.add(obj);
			}
			return lista;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

}
