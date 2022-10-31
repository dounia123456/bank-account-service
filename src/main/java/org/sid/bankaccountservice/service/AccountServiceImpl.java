package org.sid.bankaccountservice.service;

import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.mappers.AccountMapper;
import org.sid.bankaccountservice.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional   // cad que tt les methodes sont transactionnels
public class AccountServiceImpl implements AccountService {
    @Autowired     //injection de dependance
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        // ca c le mapping je map des DTO vers les entités -> bonnes pratiques
        BankAccount bankAccount= BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
                //g transferé les dto vers  entité
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount); // le code metier
        BankAccountResponseDTO bankAccountResponseDTO  = accountMapper.fromBankAccount(savedBankAccount);
        //il ne faut jamis faire ce code la dans la couche service-> autre classe bank account mapper


        return bankAccountResponseDTO;
    }
}
