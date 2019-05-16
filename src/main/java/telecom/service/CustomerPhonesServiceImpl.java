/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telecom.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import telecom.Customer;
import telecom.PhoneNumber;

@Service
public class CustomerPhonesServiceImpl implements CustomerPhonesService{

    @Override
    public List<Customer> initialData() {
        List<Customer> customers = Arrays.asList(
            new Customer(new Long(1),"John",Arrays.asList(
                new PhoneNumber("+447653127771",true),
                new PhoneNumber("+447653127772",false),
                new PhoneNumber("+447653127773",true)
            )),
            new Customer(new Long(2),"Mark",Arrays.asList(
                new PhoneNumber("+447653127774",true),
                new PhoneNumber("+447653127775",false),
                new PhoneNumber("+447653127776",true)
            )),
            new Customer(new Long(3),"David",Arrays.asList(
                new PhoneNumber("+447653127777",true),
                new PhoneNumber("+447653127778",false),
                new PhoneNumber("+447653127779",true)
            )),
            new Customer(new Long(4),"Susan",Arrays.asList(
                new PhoneNumber("+447653127781",false),
                new PhoneNumber("+447653127782",true),
                new PhoneNumber("+447653127783",true)
            )),
            new Customer(new Long(5),"Joe",Arrays.asList(
                new PhoneNumber("+447653127784",false),
                new PhoneNumber("+447653127785",true),
                new PhoneNumber("+447653127786",true)
            ))
        );
        return customers;
    }
    
    @Override
    public List<PhoneNumber> getAllPhoneNumbers(List<Customer> customers) {
        return customers.stream()
                .map(c -> c.getPhoneNumbers())
                .flatMap(pnList -> pnList.stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<PhoneNumber> getPhoneNumbersByCustomerId(Long id,List<Customer> customers) {
        Customer customer = customers.stream().filter(c -> c.getId().equals(id))
                .findAny().orElse(null);
        if(customer == null){
            return new ArrayList<>();
        }
        return customer.getPhoneNumbers();
    }

    @Override
    public PhoneNumber activePhoneNumber(String number,List<Customer> customers) {
        PhoneNumber phoneNumber = customers.stream()
                .map(c -> c.getPhoneNumbers())
                .flatMap(pnList -> pnList.stream())
                .filter(pn -> pn.getNumber().equals(number))
                .findAny().orElse(null);
        
        if(phoneNumber != null){
            phoneNumber.setActivated(true);
        }
        
        return phoneNumber;        
    }
    
}
