package org.sid.bankaccountservice.repository;

import org.sid.bankaccountservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

//BankAccount c l'entité qu'on veut gerer  id de type string

public interface BankAccountRepository extends JpaRepository <BankAccount,String>{
}
