package application;

import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;


public class PragramaDep {

	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			DepartmentDao departDao = DaoFactory.createDepartmentDao();
			System.out.println("-----------Teste 1 findbyId -------------");
			System.out.print("Buscar por id:");
			int id = sc.nextInt();
			Department depart = departDao.findById(id);
			System.out.println(depart);
			
			System.out.println("\n-----------Teste 2 findAll -------------");
			List <Department> lista = departDao.findAll();
			for(Department obj : lista) {
				System.out.println(obj);
			}
			
			System.out.println("\n-----------Teste 3 DepartmentInsert -------------");
			Department newDp = new Department(null,"D2");
			departDao.insert(newDp);
			System.out.println("Novo departamento inserido, id="+newDp.getId());
			
			System.out.println("\n-----------Teste 4 DepartmentUpdate -------------");
			newDp.setNome("Brinquedos");
			departDao.update(newDp);
			System.out.println("Dados atualizados!");
			
			System.out.println("\n-----------Teste 5 DepartmentDelete -------------");
			System.out.print("Deletar id:");
			id = sc.nextInt();
			departDao.deleteById(id);
			System.out.println("Registro apagado!");
			
			DB.closeConnection();
			sc.close();
	}

}
