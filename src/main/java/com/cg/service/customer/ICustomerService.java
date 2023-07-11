package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Transfer;
import com.cg.model.Withdraw;
import com.cg.service.IGeneralService;

import java.math.BigDecimal;
import java.util.List;

public interface ICustomerService extends IGeneralService<Customer, Long> {
    List<Customer> findAllByDeletedIsFalse();
    List<Customer> findAllByIdNot(Long id);
    Boolean existsByEmail(String email);
    void incrementBalance(Long id, BigDecimal amount);
    Customer deposit(Deposit deposit);
    Customer withdraw(Withdraw withdraw);
    void transfer(Transfer transfer);
}