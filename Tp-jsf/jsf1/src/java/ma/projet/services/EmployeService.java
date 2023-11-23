/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ma.projet.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ma.projet.beans.Employe;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author LACHGAR
 */
public class EmployeService implements IDao<Employe>{

    @Override
    public boolean create(Employe o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Employe o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Employe o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.flush();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Employe getById(int id) {
        Employe employe  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employe  = (Employe) session.get(Employe.class, id);
        session.getTransaction().commit();
        return employe;
    }

    @Override
    public List<Employe> getAll() {
        List <Employe> employes = null;
      
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employes  = session.createQuery("from Employe").list();
        session.getTransaction().commit();
        return employes;
    }
    
    
    public List<Object[]> nbemploye(){
        List<Object[]> employes = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employes  = session.createQuery("select count(m.marque), m.marque from Employe m group by m.marque").list();
        session.getTransaction().commit();
        return employes;
    }
    
    public List<Employe> getbydates(Date d1 , Date d2){
        List <Employe> employes = new ArrayList<>();
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
         employes  = session.createQuery("from Employe m where m.dateAchat between :d1 and :d2").setParameter("d1", d1).setParameter("d2", d2).list();
        session.getTransaction().commit();
        return employes;
        
    }
    public List<Object[]> nbEmployeByChef() {
    List<Object[]> Employes = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Employes = session.createQuery("select count(e.nom), e.chef.nom from Employe e group by e.chef.nom").list();
    session.getTransaction().commit();
    return Employes;
}
}
