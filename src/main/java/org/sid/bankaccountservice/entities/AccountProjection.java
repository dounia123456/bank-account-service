package org.sid.bankaccountservice.entities;

import org.sid.bankaccountservice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

//rest ne permet pas a l utilisateur de preciser les colonnes qu il veut c pour ca on utilise les projections

//ca c du spring datarest

//il s applique a la classe bankccount


//avec graphql les projections se trouvent dans les requetes

@Projection(types = BankAccount.class, name ="p1")

public interface AccountProjection {

    //dans ce cas la on va just projeter id et le type grace a localhost:8081/bankAccounts?projection=p1
    public String getId();
    public AccountType getType();
    public Double getBalance();//il faut respecter la convention des getters



}

