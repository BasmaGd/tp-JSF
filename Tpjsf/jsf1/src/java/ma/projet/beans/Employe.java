/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Employe implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String nom;
    private String prenom;
    private String photo;
       

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    @ManyToOne
    private Service service;
    @ManyToOne
    private Employe chef;

    public Employe() {
    }

    public Employe(String nom, String prenom, String photo, Date dateNaissance, Service service, Employe chef) {
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.dateNaissance = dateNaissance;
        this.service = service;
        this.chef = chef;
        

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Employe getChef() {
        return chef;
    }

    public void setChef(Employe chef) {
        this.chef = chef;
    }

    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", photo=" + photo + ", dateNaissance=" + dateNaissance + ", service=" + service + ", chef=" + chef + '}';
    }
//public boolean estChef() {
//        return this.chef != null; // Retourne true si l'employé a un chef, sinon false
//    }

}
