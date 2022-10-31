package org.sid.bankaccountservice.web;
// Rest API

//grace a la dependance data rst je n pas besoin d ecrire t ceci ,il va nous henere ls post et get.. et ceci marche pour toutes les entités

import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.mappers.AccountMapper;
import org.sid.bankaccountservice.repository.BankAccountRepository;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//c un service restful donc on va utiliser rest controller
@RestController
@RequestMapping("/api")
public class AccountRestController {
   // @Autowired pour l injection de dependence maias le meilleur cde les faiire via le constructeur
    //on est entrain d'injecter la couche repository car on a besoin d'acceder la BD
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService; //interface-> il faut l injecter,l ajouter au constructeur
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
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
    public BankAccountResponseDTO save (@RequestBody BankAccountRequestDTO requestDTO) {

        return accountService.addAccount(requestDTO);
        // g les dto -> appelent couchent service->appel la couche repository
    }

@PutMapping("/bankAccounts/{id}")
// la methode update fait put and patch
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
         if (bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
         if (bankAccount.getCreatedAt()!=null) account.setCreatedAt(new Date());
         if (bankAccount.getType()!=null) account.setType(bankAccount.getType());
         if (bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
}


    @DeleteMapping ("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
         bankAccountRepository.deleteById(id);
    }




}




//{}
