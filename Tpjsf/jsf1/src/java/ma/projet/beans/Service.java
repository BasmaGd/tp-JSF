/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;

@OneToMany(mappedBy = "service",fetch = FetchType.EAGER)
    private List<Employe> employes;
    public Service() {
    }

    public Service(String nom) {
        this.nom = nom;
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

    public List<Employe> getEmployes() {
        return employes;
    }
//    public List<Employe> getEmployesSansChef() {
//    List<Employe> employesSansChef = new ArrayList<>();
//    
//    if (employes != null && !employes.isEmpty()) {
//        for (Employe employe : employes) {
//            if (employe.estChef()) {
//                employesSansChef.add(employe);
//            }
//        }
//    }
    
//    return employesSansChef;
//}


    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", nom=" + nom + '}';
    }

    public Employe getChef() {
        if (employes != null && !employes.isEmpty()) {
            // Retourne le premier employé de la liste comme chef
            return employes.get(0);
        } else {
            return null;
        }
    }
    
}

