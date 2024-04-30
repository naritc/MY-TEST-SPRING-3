package my.micro.accounts.service.impl;

import lombok.AllArgsConstructor;
import my.micro.accounts.config.exception.ResourceNotFoundException;
import my.micro.accounts.dto.AccountsDto;
import my.micro.accounts.dto.CardsDto;
import my.micro.accounts.dto.CustomerDetailsDto;
import my.micro.accounts.dto.LoansDto;
import my.micro.accounts.entity.AccountsEntity;
import my.micro.accounts.entity.CustomerEntity;
import my.micro.accounts.mapper.AccountsMapper;
import my.micro.accounts.mapper.CustomerMapper;
import my.micro.accounts.repository.AccountsRepository;
import my.micro.accounts.repository.CustomerRepository;
import my.micro.accounts.service.ICustomersService;
import my.micro.accounts.service.client.CardsFeignClient;
import my.micro.accounts.service.client.LoansFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        CustomerEntity customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        AccountsEntity accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
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
