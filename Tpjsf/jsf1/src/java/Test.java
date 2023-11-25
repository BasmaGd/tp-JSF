
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class Test {
    public static void main(String[] args) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
    }
}
