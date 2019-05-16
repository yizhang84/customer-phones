package telecom;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import telecom.service.CustomerPhonesService;

@RestController
@RequestMapping("/customer-phones")
public class CustomerPhonesController {
    private List<Customer> customers;
    private final CustomerPhonesService service;
    
    @Autowired
    public CustomerPhonesController(CustomerPhonesService service){
        this.service = service;
        customers = service.initialData();
    }
    
    /* get all phone numbers.
    * Example:
    * curl -X GET http://localhost:8080/customer-phones/numbers
    */
    @GetMapping("/numbers")  
    public List<PhoneNumber> getPhoneNumbers(){
        return service.getAllPhoneNumbers(customers);
    }
    
    /* Get phone numbers of a single user by the user id
    * Example:
    * curl -X GET http://localhost:8080/customer-phones/customer/1
    */
    @GetMapping("/customer/{id}")
    public List<PhoneNumber> getPhoneNumbersByCustomerId(@PathVariable("id") Long id){
        return service.getPhoneNumbersByCustomerId(id,customers);
    }
    
    /* Activated a phone number by setting the activated status to true
    * Example:
    * curl -X PATCH http://localhost:8080/customer-phones/numbers/+447653127772
    */
    @PatchMapping("/numbers/{number}")
    public PhoneNumber activatePhoneNumber(@PathVariable("number") String number){
        return service.activePhoneNumber(number, customers);
    }
    
}
