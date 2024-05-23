package application;

import java.util.Date;

public class Patient extends Utilisateur {

//	private String date_de_naissance;
	//private String sex;

	public Patient(int id, String role, String nom, String prenom, String numTel, String email, Date dateDeNaissance, String sexe) {
		super(id,role , nom , prenom , numTel , email, dateDeNaissance, sexe);
		//this.date_de_naissance = date_de_naissance;
		//this.sex = sex;

	}

	
}