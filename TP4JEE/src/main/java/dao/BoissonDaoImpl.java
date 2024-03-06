package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.SingletonConnection;

import metier.entities.Boisson;

public class BoissonDaoImpl implements IBoissonDao {

	@Override
	public Boisson save(Boisson b) {
		Connection conn=SingletonConnection.getConnection();
		 try {
		PreparedStatement ps= conn.prepareStatement("INSERT INTO boissonS(NOM_Boisson,PRIX) VALUES(?,?)");
		ps.setString(1, b.getNomBoisson());
		ps.setDouble(2, b.getPrix());
		ps.executeUpdate();
		PreparedStatement ps2= conn.prepareStatement
		("SELECT MAX(ID_Boisson) as MAX_ID FROM boissonS");
		ResultSet rs =ps2.executeQuery();
		if (rs.next()) {
		b.setIdBoisson(rs.getLong("MAX_ID"));
		}
		ps.close();
		ps2.close();
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return b;
	}

	@Override
	public List<Boisson> boissonsParMC(String mc) {
		List<Boisson> bois= new ArrayList<Boisson>();
		 Connection conn=SingletonConnection.getConnection();
		 try {
		PreparedStatement ps= conn.prepareStatement("select * from boissonS where NOM_Boisson LIKE ?");
		ps.setString(1, "%"+mc+"%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Boisson b = new Boisson();
		b.setIdBoisson(rs.getLong("ID_Boisson"));
		b.setNomBoisson(rs.getString("NOM_Boisson"));
		b.setPrix(rs.getDouble("PRIX"));
		bois.add(b);
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return bois;
	}

	@Override
	public Boisson getBoisson(Long id) {
		 Connection conn=SingletonConnection.getConnection();
		 Boisson b = new Boisson();
		 try {
		PreparedStatement ps= conn.prepareStatement("select * from boissons where ID_Boisson = ?");
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
		b.setIdBoisson(rs.getLong("ID_Boisson"));
		b.setNomBoisson(rs.getString("NOM_Boisson"));
		b.setPrix(rs.getDouble("PRIX"));
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return b;

	}

	@Override
	public Boisson updateBoisson(Boisson b) {
		Connection conn=SingletonConnection.getConnection();
		 try {
			 PreparedStatement ps= conn.prepareStatement("UPDATE boissons SET NOM_Boisson=?,PRIX=? WHERE ID_Boisson=?");
			 ps.setString(1, b.getNomBoisson());
			 ps.setDouble(2, b.getPrix());
			 ps.setLong(3, b.getIdBoisson());
			 ps.executeUpdate();
			 ps.close();
			 } catch (SQLException e) {
			 e.printStackTrace();
			 }
			 return b;
	}

	@Override
	public void deleteBoisson(Long id) {
		Connection conn=SingletonConnection.getConnection();
		 try {
		PreparedStatement ps= conn.prepareStatement("DELETE FROM boissons WHERE ID_Boisson = ?");
		ps.setLong(1, id);
		ps.executeUpdate();
		ps.close();
		} catch (SQLException e) {
		e.printStackTrace();
		}
		
	}

}
