package com.raja.accounts.service.impl;

import com.raja.accounts.dto.AccountsDto;
import com.raja.accounts.dto.CardsDto;
import com.raja.accounts.dto.CustomerDetailsDto;
import com.raja.accounts.dto.LoansDto;
import com.raja.accounts.entity.Accounts;
import com.raja.accounts.entity.Customer;
import com.raja.accounts.exception.ResourceNotFoundException;
import com.raja.accounts.mapper.AccountsMapper;
import com.raja.accounts.mapper.CustomerMapper;
import com.raja.accounts.repository.AccountsRepository;
import com.raja.accounts.repository.CustomerRepository;
import com.raja.accounts.service.ICustomersService;
import com.raja.accounts.service.client.CardsFeignClient;
import com.raja.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
