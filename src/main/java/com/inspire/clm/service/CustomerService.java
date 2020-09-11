package com.inspire.clm.service;

import com.inspire.clm.dao.CustomerDAO;
import com.inspire.clm.exception.CustomerNotFoundException;
import com.inspire.clm.model.Customer;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    private int customerIdCount = 1;
    private List<Customer> customerList = new CopyOnWriteArrayList<>();

    public Customer addCustomer(Customer customer) {
        /*customer.setCusId(customerIdCount);
        customerList.add(customer);
        customerIdCount ++;
        return customer;*/
        return customerDAO.save(customer);
    }

    public List<Customer> getCustomers(){
        return customerDAO.findAll();
        //return customerList;
    }

    public Customer getCustomer(int customerId){
        /*return customerList
                .stream()
                .filter(cust -> cust.getCusId() == customerId)
                .findFirst()
                .get();*/

        Optional<Customer> optionalCustomer = customerDAO.findById(customerId);

        if(!optionalCustomer.isPresent())
            throw new CustomerNotFoundException("Customer record not found");

        return optionalCustomer.get();
    }

    public Customer updateCustomer(int customerId, Customer customer){
        customer.setCusId(customerId);
        /*customerList
                .stream()
                .forEach(c -> {
                    if (c.getCusId() == customerId) {
                        c.setCusFirstName(customer.getCusFirstName());
                        c.setCusLastName(customer.getCusLastName());
                        c.setCusEmail(customer.getCusEmail());
                    }
                });
        return customerList
                .stream()
                .filter(c -> c.getCusId() == customerId)
                .findFirst()
                .get();*/
        return customerDAO.save(customer);
    }

    public void deleteCustomer(int customerId){
        /*customerList
                .stream()
                .forEach(c -> {
                    if(c.getCusId() == customerId){
                        customerList.remove(c);
                    }
                });*/
        customerDAO.deleteById(customerId);
    }
}
