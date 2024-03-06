package dao;

import java.util.List;

import metier.entities.Boisson;

public interface IBoissonDao {
	public Boisson save(Boisson b);
	public List<Boisson> boissonsParMC(String mc);
	public Boisson getBoisson(Long id);
	public Boisson updateBoisson(Boisson b);
	public void deleteBoisson(Long id);
}
