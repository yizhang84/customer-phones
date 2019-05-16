package telecom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerPhonesController.class)
@ComponentScan({"telecom.service"})
public class CustomerPhonesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testGetAllPhoneNumbers() throws Exception{
        mockMvc.perform(get("/customer-phones/numbers"))    
        .andExpect(status().isOk());
    }
    
    @Test
    public void testGetPhoneNumbersByCustomerId() throws Exception{
        ResultActions action = mockMvc.perform(get("/customer-phones/customer/{id}",new Long(1)));
        MvcResult result = action.andExpect(status().isOk()).andReturn();
        
        if(result.getResponse().getContentLength() != 0){
            action.andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
        }
    }
    
    @Test
    public void testGetPhoneNumbersByCustomerIdWithNonExistCustomer() throws Exception{
        mockMvc.perform(get("/customer-phones/customer/{id}",new Long(0)))    
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

    }
    
    @Test
    public void testActivatePhoneNumber()throws Exception{
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.patch("/customer-phones/numbers/{number}","+447653127772"));
        MvcResult result = action.andExpect(status().isOk()).andReturn();
        
        if(result.getResponse().getContentLength() != 0){
            action.andExpect(MockMvcResultMatchers.jsonPath("$.activated").value("true"))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.number").value("+447653127772"));  
        }      
    }
}
