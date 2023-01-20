package net.youssfi;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.youssfi.model.BankAccount;

import java.awt.geom.CubicCurve2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.youssfi.model.CurrentAccount;
import net.youssfi.model.SavingAccount;

public class App2 {
    public static void main(String[] args) throws JsonProcessingException {

        BankAccount [] accounts=new BankAccount[4];
        accounts[0]=new CurrentAccount("USD",5400,500);
        accounts[1]=new CurrentAccount("MAD",1290, 4300);
        accounts[2]=new SavingAccount("USD",3200,4.6);
        accounts[3]=new SavingAccount("MAD",5400,4.3);

        for (BankAccount acc:accounts){
            /*
            if(acc instanceof CurrentAccount){
                System.out.println(((CurrentAccount)acc).getType());
            } else if(acc instanceof SavingAccount){
                System.out.println(((SavingAccount)acc).getType());
            }
             */
            System.out.println(acc.getType());
            if(acc instanceof SavingAccount){
                System.out.println("Rate="+((SavingAccount)acc).getInterestRate());
            }
            if(acc instanceof CurrentAccount){
                System.out.println("Overdraft="+((CurrentAccount)acc).toString());
            }
        }
        System.out.println("Listes");
        List<BankAccount> bankAccountList=new ArrayList<>();
        bankAccountList.add(new CurrentAccount("MAD",5400, 4500) );
        bankAccountList.add(new CurrentAccount("MAD",4300,4300) );
        bankAccountList.add(new SavingAccount("MAD",5400,3) );
        bankAccountList.add(new SavingAccount("MAD",234,4) );
        bankAccountList.add(new CurrentAccount("MAD",65,2) );

        for (BankAccount acc:accounts){
            System.out.println(acc.getBalance());
        }

        System.out.println("========= Map =========");
        Map<String, BankAccount> bankAccountMap=new HashMap<>();
        bankAccountMap.put("cc1",new CurrentAccount("MAD",4300,43) );
        bankAccountMap.put("cc2",new SavingAccount("MAD",2300,3) );
        bankAccountMap.put("cc3",new CurrentAccount("MAD",3220,4300) );
        bankAccountMap.put("cc4",new SavingAccount("MAD",76,4.3) );

        BankAccount acc=bankAccountMap.get("cc2");
        System.out.println(acc.toString());

        for (String key : bankAccountMap.keySet()){
            System.out.println(bankAccountMap.get(key));
        }
        System.out.println("============");
        for (BankAccount ba:bankAccountMap.values()){
            System.out.println(toJson(ba));
        }


    }

    public static String toJson(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }
}
