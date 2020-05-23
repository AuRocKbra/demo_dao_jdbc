package application;


import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Programa {

	public static void main(String[] args) {
		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println("-----------Teste 1 findbyId -------------");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n-----------Teste 2 findbyDepartment -------------");
		Department depart = new Department(2, null);
		List<Seller> seller2 = sellerDao.findByDepartment(depart);
		for(Seller obj : seller2) {
			System.out.println(obj);
		}

	}

}
