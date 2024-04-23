package com.bank.managment.configurations;

import com.bank.managment.entities.Customers;
import com.bank.managment.services.CustomerService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Data
@Component
public class Authorization {
    @Autowired
    CustomerService customerService;

    private String email;
    private String password;

    public boolean authenticate(){
        boolean b = false;
        String email = getEmail();
        String password = getPassword();
        Customers customerByEmailAndPassword = customerService.getCustomerByEmailAndPassword(email, password);
        if(customerByEmailAndPassword!=null){
            b = true;
        }
        return b;
    }

    public void logout(){
        setEmail(null);
        setPassword(null);
    }


}
