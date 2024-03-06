package test;
import java.util.List;

import dao.BoissonDaoImpl;
import metier.entities.Boisson;

public class TestDao {

	public static void main(String[] args) {
		BoissonDaoImpl pdao= new BoissonDaoImpl();
		Boisson bois= pdao.save(new Boisson("coctail",5.5));
		System.out.println(bois);
		List<Boisson> boi =pdao.boissonsParMC("jus");
		for (Boisson b : boi)
		System.out.println(b);

	}

}
