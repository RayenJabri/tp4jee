package web;

import java.util.ArrayList;
import java.util.List;

import metier.entities.Boisson;

public class BoissonModele {private String motCle;
List<Boisson> boissons = new ArrayList<>();
public String getMotCle() {
return motCle;
}
public void setMotCle(String motCle) {
this.motCle = motCle;
}
public List<Boisson> getBoissons() {
return boissons;
}
public void setBoissons(List<Boisson> boissons) {
this.boissons= boissons;
}

}
