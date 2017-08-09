package com.cg.billing.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.exceptions.BillingServicesDownException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;

import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.exceptions.BillingServicesDownException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;

@Repository
public class BillingDaoImpl implements IBillingDao{
	
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public Customer insertCustomer(Customer customer) throws BillingServicesDownException {
		
		em.persist(customer);
		em.flush();
		return customer;
	}

	@Override
	public long insertPostPaidAccount(int customerID, PostpaidAccount account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updatePostPaidAccount(int customerID, PostpaidAccount account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double insertMonthlybill(int customerID, long mobileNo, Bill bill) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertPlan(Plan plan) throws PlanDetailsNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deletePostPaidAccount(int customerID, long mobileNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bill getMonthlyBill(int customerID, long mobileNo, String billMonth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bill> getCustomerPostPaidAccountAllBills(int customerID, long mobileNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostpaidAccount> getCustomerPostPaidAccounts(int customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomer(int customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Plan> getAllPlans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plan getPlan(int planID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostpaidAccount getCustomerPostPaidAccount(int customerID, long mobileNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostpaidAccount getPlanDetails(int customerID, long mobileNo) throws PostpaidAccountNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCustomer(int customerID) {
		// TODO Auto-generated method stub
		return false;
	}

}
