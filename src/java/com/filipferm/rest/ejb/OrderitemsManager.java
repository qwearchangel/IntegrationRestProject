package com.filipferm.rest.ejb;

import com.filipferm.rest.model.Orderitems;
import com.filipferm.rest.model.Orders;
import com.filipferm.rest.model.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Filip
 */
@Stateless
public class OrderitemsManager{
    @PersistenceContext(name = "IntegrationRest_SoapPU")
    EntityManager em;

    public List<Orderitems> getAllObj() {
        return em.createNamedQuery("Orderitems.findAll", Orderitems.class).getResultList();
    }
    /**
     * 
     * @param id The Orderitems object id
     * @return the Orderitem object
     */
    public Orderitems getObjById(int id) {
        return em.find(Orderitems.class, id);
    }

    public void deleteObj(Orderitems obj) {
        em.remove(getObjById(obj.getId()));
    }

    public void addObj(Orderitems obj,int oid, int pid) {
        Orderitems orderitem = obj;
        orderitem.setOrder(em.find(Orders.class, oid)); 
        orderitem.setProduct(em.find(Product.class, pid));
        em.merge(orderitem);
    }
}
