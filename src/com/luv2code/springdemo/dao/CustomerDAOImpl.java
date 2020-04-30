package com.luv2code.springdemo.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

/**
 * @author bhaskar
 *
 * 
 */

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	
	public List<Customer> getCustomers() {
		
		//get the hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		// create sort by lastname
				//create query
		Query<Customer> theQuery=
				currentSession.createQuery("from Customer order by lastName", Customer.class);;
		//execute query
		List<Customer> customers=theQuery.getResultList();		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//save/update the customer
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		
		Session curreSession=sessionFactory.getCurrentSession();
		Customer theCustomer=curreSession.get(Customer.class, theId);
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		//get current hibernate session
				Session currentSession=sessionFactory.getCurrentSession();
				
			//delete the customer
				Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
				theQuery.setParameter("customerId", theId);
				theQuery.executeUpdate();
	}

}
