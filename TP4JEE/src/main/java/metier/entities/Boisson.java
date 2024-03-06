package metier.entities;

import java.io.Serializable;

public class Boisson implements Serializable {
	private Long idBoisson ;
	private  String nomBoisson;
	private double prix;
	public  Boisson(){
		super();
	}
	
public Boisson(String nomBoisson, double prix) {
	super();
	this.nomBoisson = nomBoisson;
	this.prix = prix;
}

public long getIdBoisson() {
	return idBoisson;
}
public void setIdBoisson (Long idBoisson) {
	this.idBoisson=idBoisson;
}	
public String getNomBoisson() {
	return nomBoisson;
}
public void setNomBoisson(String nomBoisson) {
	this.nomBoisson=nomBoisson;
}
public double getPrix() {
return prix;
}
public void setPrix(double prix) {
this.prix = prix;
}
@Override
public String toString() {
	return "Boisson [idBoisson=" + idBoisson+ ", nomBoisson=" +
			nomBoisson + ", prix=" + prix + "]";
}

}
