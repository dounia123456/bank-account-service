package org.sid.bankaccountservice.service;

// couche METIER


import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.repository.BankAccountRepository;

//l ajout d un ompte ne va pas directement a la bd i faut passer par le processus metier


public interface AccountService {
     BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

}
//{}
