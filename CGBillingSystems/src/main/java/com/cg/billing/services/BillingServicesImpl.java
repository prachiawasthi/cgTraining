package com.cg.billing.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.dao.BillingDaoImpl;
import com.cg.billing.exceptions.BillDetailsNotFoundException;
import com.cg.billing.exceptions.BillingServicesDownException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.InvalidBillMonthException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;

@Service
@Transactional
public class BillingServicesImpl implements IBillingServices {
	
	@Autowired
	BillingDaoImpl dao;

	@Override
	public List<Plan> getPlanAllDetails() throws BillingServicesDownException {
		
		return dao.getAllPlans();
	}

	@Override
	public long openPostpaidMobileAccount(int customerID, int planId, PostpaidAccount account)
			throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException, BillingServicesDownException {
		System.out.println("Service");
		Plan plan=new Plan();
		plan.setPlanID(planId);
		if(planId==199){
			plan.setFreeInternetDataUsageUnits(250);
			plan.setFreeLocalCalls(100);
			plan.setFreeLocalSMS(50);
			plan.setFreeStdCalls(400);
			plan.setFreeStdSMS(50);
			plan.setInternetDataUsageRate(2.5f);
			plan.setLocalCallRate(0.2f);
			plan.setLocalSMSRate(1);
			plan.setMonthlyRental(199);
			plan.setPlanCircle("Delhi");
			plan.setPlanName("199 Dhamakedar Plan");
			plan.setStdCallRate(6.5f);
			plan.setStdSMSRate(1.5f);
		}
		else if(planId==299){
			plan.setFreeInternetDataUsageUnits(500);
			plan.setFreeLocalCalls(400);
			plan.setFreeLocalSMS(100);
			plan.setFreeStdCalls(800);
			plan.setFreeStdSMS(100);
			plan.setInternetDataUsageRate(2.5f);
			plan.setLocalCallRate(0.2f);
			plan.setLocalSMSRate(1);
			plan.setMonthlyRental(299);
			plan.setPlanCircle("Delhi");
			plan.setPlanName("299 Silver Plan");
			plan.setStdCallRate(6.5f);
			plan.setStdSMSRate(1.5f);
		}
		account.setPlan(plan);
		return dao.insertPostPaidAccount(customerID, account);
	}

	@Override
	public double generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS,
			int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits, int planId)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillingServicesDownException, PlanDetailsNotFoundException {

		return 0;
	}

	@Override
	public Customer getCustomerDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
	
		return dao.getCustomer(customerID);
	}

	@Override
	public List<Customer> getAllCustomerDetails() throws BillingServicesDownException {
		
		return dao.getAllCustomers();
	}

	@Override
	public PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		
		return dao.getCustomerPostPaidAccount(customerID, mobileNo);
	}

	@Override
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		
		return dao.getCustomerPostPaidAccounts(customerID);
	}

	@Override
	public Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillDetailsNotFoundException, BillingServicesDownException {
		
		return dao.getMonthlyBill(customerID, mobileNo, billMonth);
	}

	@Override
	public List<Bill> getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		
		return dao.getCustomerPostPaidAccountAllBills(customerID, mobileNo);
	}

	@Override
	public boolean changePlan(int customerID, long mobileNo, int planID) throws CustomerDetailsNotFoundException,
			PostpaidAccountNotFoundException, PlanDetailsNotFoundException, BillingServicesDownException {
		return false;
	}

	@Override
	public boolean closeCustomerPostPaidAccount(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		return dao.deletePostPaidAccount(customerID, mobileNo);
	}

	@Override
	public boolean deleteCustomer(int customerID)
			throws BillingServicesDownException, CustomerDetailsNotFoundException {
		return dao.deleteCustomer(customerID);
	}

	@Override
	public PostpaidAccount getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException,
			PlanDetailsNotFoundException {
		return dao.getPlanDetails(customerID, mobileNo);
	}

	@Override
	public Customer acceptCustomerDetails(Customer customer) throws BillingServicesDownException {
		
		return dao.insertCustomer(customer);
	}

	@Override
	public boolean authenticateCustomer(Customer customer)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		return false;
	}

}
