/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telecom.service;

import java.util.List;
import telecom.Customer;
import telecom.PhoneNumber;

public interface CustomerPhonesService {
    /*
    * inital the data
    * @return List<Customer>
    */
    public List<Customer> initialData();
    
    /*
    * Get all phone numbers
    * @param List<Customer> customers
    * @return List<PhoneNumber>
    */
    public List<PhoneNumber> getAllPhoneNumbers(List<Customer> customers);
    
    /*
    * Get phone numbers of a single user
    * @param Long id,List<Customer> customers
    * @return List<PhoneNumber>
    */
    public List<PhoneNumber> getPhoneNumbersByCustomerId(Long id,List<Customer> customers);
    
    /*
    * Activated a phone number by setting the activated status to true
    * @param String number,List<Customer> customers
    * @return PhoneNumber Object
    */
    public PhoneNumber activePhoneNumber(String number,List<Customer> customers);
}
