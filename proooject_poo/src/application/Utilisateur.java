package application;

import java.util.Date;

public class Utilisateur {
    private int id;
    private String role;
    private String nom;
    private String prenom;
    private String numTel;
    private String email;
    private Date dateDeNaissance;
    private String sexe;

    public Utilisateur(int id, String role, String nom, String prenom, String numTel, String email, Date dateDeNaissance, String sexe) {
        this.id = id;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
        this.sexe = sexe;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getNumTel() { return numTel; }
    public void setNumTel(String numTel) { this.numTel = numTel; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getDateDeNaissance() { return dateDeNaissance; }
    public void setDateDeNaissance(Date dateDeNaissance) { this.dateDeNaissance = dateDeNaissance; }

    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }
}