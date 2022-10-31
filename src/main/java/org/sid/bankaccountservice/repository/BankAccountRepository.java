package org.sid.bankaccountservice.repository;

import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

//BankAccount c l'entité qu'on veut gerer  id de type string
//je demande a spring au demarrage de me deramerrer un web service rest full qui permet de gerer les entites de type bank accounk, il cree les entites get put post..
@RepositoryRestResource

public interface BankAccountRepository extends JpaRepository <BankAccount,String>{
 //avec rest controller il faut ajouter une methode qui faitr appel a cette methode
    //avec spring data rest on peut y acceder directly
   //ON CREE DES ALIAS pour le nom de la metode et pour le parametre
    @RestResource(path = "/byType") //car c pas ben d appeler les annotations par leur nom
    List<BankAccount> findByType(@Param("t") AccountType type);

    //pour etre dans les normes il faut respecter les dto et utiliser la couche service
}
