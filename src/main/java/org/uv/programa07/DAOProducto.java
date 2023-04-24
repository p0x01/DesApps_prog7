/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.programa07;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAOProducto implements IDAOGeneral<Producto, Long>{

    @Override
    public Producto create(Producto p) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        session.save(p);
        t.commit();
        session.close();
        return p;
    }

    @Override
    public boolean delete(Long id) {
        boolean b;
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        
        Producto p = findById(id);
        if (p != null){
            session.delete(p);
            Logger.getLogger(Producto.class.getName()).log(Level.INFO, "Se elimino producto");
            b=true;
        }else{
            b=false;
        }
        
        t.commit();
        session.close();
        return b;
    }

    @Override
    public Producto update(Producto p, Long id) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        session.update(p);
        t.commit();
        session.close();
        return p;
    }

    @Override
    public List<Producto> findAll() {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        List<Producto> lstProd=
                session.createQuery("from Producto",Producto.class).list();
        t.commit();
        session.close();
        return lstProd;
    }

    @Override
    public Producto findById(Long id) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        Producto p=session.get(Producto.class, id);
        t.commit();
        session.close();
        return p;
    }
    
}
