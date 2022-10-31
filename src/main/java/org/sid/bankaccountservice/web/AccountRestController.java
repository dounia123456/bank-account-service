package org.sid.bankaccountservice.web;
// Rest API

import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//c un service restful donc on va utiliser rest controller
@RestController
public class AccountRestController {
   // @Autowired pour l injection de dependence maias le meilleur cde les faiire via le constructeur
    //on est entrain d'injecter la couche repository car on a besoin d'acceder la BD
    private BankAccountRepository bankAccountRepository;

    public AccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    //les entittes jpa je les utilise dans la couche repository pour le moment y a pas de dto -->mauvaise pratique
    //dans rest la norme c qd l entite s'appl xx on utilise "/xxs"-->1ere resgle de la norme restful
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Acount %s not found",id)));
    }
@PostMapping("/bankAccounts")
// la methode update fait put and patch
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
         if (account.getBalance()!=null) account.setBalance(bankAccount.getBalance());
         if (account.getCreatedAt()!=null) account.setCreatedAt(new Date());
         if (account.getType()!=null) account.setType(bankAccount.getType());
         if (account.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
}


    @DeleteMapping ("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
         bankAccountRepository.deleteById(id);
    }


}




//{}
