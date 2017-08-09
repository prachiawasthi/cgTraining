package com.cg.billing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.billing.beans.Customer;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.exceptions.BillingServicesDownException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;
import com.cg.billing.services.IBillingServices;

@RestController
public class BillingController {

	@Autowired
	private IBillingServices services;

	@RequestMapping(value = "/acceptCustomerDetail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> acceptProductDetail(@ModelAttribute Customer customer)
			throws BillingServicesDownException {
		services.acceptCustomerDetails(customer);
		return new ResponseEntity<>("Customer details succesfully added", HttpStatus.OK);
	}

	@RequestMapping(value = { "/CustomerDetails/{customerID}" }, headers = "Accept=application/json")
	public ResponseEntity<Customer> getCustomerDetails(@PathVariable("customerID") int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		Customer customer = services.getCustomerDetails(customerID);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@RequestMapping(value = { "/allCustomerDetailsJSON" }, headers = "Accept=application/json")
	public ResponseEntity<ArrayList<Customer>> getAllCustomerDetails() throws BillingServicesDownException {
		ArrayList<Customer> customerList = (ArrayList<Customer>) services.getAllCustomerDetails();
		return new ResponseEntity<>(customerList, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteCustomerDetail/{customerID}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCustomerDetail(@PathVariable("customerID") int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		boolean customer = services.deleteCustomer(customerID);
		if (customer == false)
			throw new CustomerDetailsNotFoundException(
					"Customer details with Customer ID  " + customerID + " not Found");
		return new ResponseEntity<>("Customer deleted Successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/openPostpaidMobileAccount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> openPostpaidMobileAccount(@ModelAttribute PostpaidAccount account)
			throws BillingServicesDownException, PlanDetailsNotFoundException, CustomerDetailsNotFoundException {
		System.out.println("Controller");
		services.openPostpaidMobileAccount(account.getCustomer().getCustomerID(), account.getPlan().getPlanID(),
				account);
		return new ResponseEntity<>(
				"PostPaid account details succesfully added with mobile number" + account.getMobileNo(), HttpStatus.OK);
	}

	@RequestMapping(value = {
			"/CustomerAccountDetails/{customerID}" }, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public ResponseEntity<List<PostpaidAccount>> getCustomerAccountDetails(@PathVariable("customerID") int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException, PostpaidAccountNotFoundException {
		List<PostpaidAccount> accounts = services.getCustomerAllPostpaidAccountsDetails(customerID);
		if (accounts == null)
			throw new PostpaidAccountNotFoundException(
					"Account Details with Customer ID  " + customerID + " not found");
		return new ResponseEntity<List<PostpaidAccount>>(accounts, HttpStatus.OK);
	}

	@RequestMapping(value = {
			"/CustomerAccountDetailsUsingMobile/{customerID}/{mobileNo}" }, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public ResponseEntity<PostpaidAccount> getCustomerAccountDetailsUsingMobile(
			@PathVariable("customerID") int customerID, @PathVariable("mobileNo") long mobileNo)
			throws CustomerDetailsNotFoundException, BillingServicesDownException, PostpaidAccountNotFoundException,
			PlanDetailsNotFoundException {
		PostpaidAccount account = services.getCustomerPostPaidAccountPlanDetails(customerID, mobileNo);
		if (account == null)
			throw new PostpaidAccountNotFoundException(
					"Account Details with Customer ID & Mobile No: " + customerID + "&" + mobileNo + " not found");
		return new ResponseEntity<PostpaidAccount>(account, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletePostpaidAccount/{customerID}/{mobileNo}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePostpaidAccount(@PathVariable("customerID") int customerID, 
			@PathVariable("mobileNo") long mobileNo)
			throws CustomerDetailsNotFoundException, BillingServicesDownException, PostpaidAccountNotFoundException {
		boolean account = services.closeCustomerPostPaidAccount(customerID, mobileNo);
		System.out.println("abcd");
		if (account == false)
			throw new PostpaidAccountNotFoundException(
					"Postpaid Account details with Customer ID  " + customerID + "and mobile No. "+mobileNo+" not Found");
		return new ResponseEntity<>("Customer deleted Successfully", HttpStatus.OK);
	}

}
