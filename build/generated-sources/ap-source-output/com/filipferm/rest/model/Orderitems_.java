package com.filipferm.rest.model;

import com.filipferm.rest.model.Orders;
import com.filipferm.rest.model.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-09T16:08:32")
@StaticMetamodel(Orderitems.class)
public class Orderitems_ { 

    public static volatile SingularAttribute<Orderitems, Orders> ordersid;
    public static volatile SingularAttribute<Orderitems, Integer> quantity;
    public static volatile SingularAttribute<Orderitems, Product> productid;
    public static volatile SingularAttribute<Orderitems, Double> totalPrice;
    public static volatile SingularAttribute<Orderitems, Integer> id;

}