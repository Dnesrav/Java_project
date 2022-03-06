
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Atm 
{
	String userName;
	int accNo;
	long mobileNumber;
	int accBalance;
	int atmPin;
	String bankName;
	int debitTrans[]=new int[10];
	int creditTrans[]=new int[10];
	int debitCnt=0,creditCnt=0;
	
	public Atm(String userName,int accNo,long mobileNumber,int accBalance,int atmPin,String bankName) 
	{
		this.userName=userName;
		this.accNo=accNo;
		this.mobileNumber=mobileNumber;
		this.accBalance=accBalance;
		this.atmPin=atmPin;
		this.bankName=bankName;
	}
	
	public int login(int pin) 
	{
		if(atmPin==pin) 
			return 1;
		return 0;
	}
	public void withdraw(int balance)
	{
		if(accBalance-balance>=0) {
		accBalance=accBalance-balance;
		System.out.println(balance+"withdrawn. Current Balance is "+accBalance);
		debitTrans[debitCnt++]= balance;
		}
		else
			System.out.println("You have Insufficient Balance. Current Balance is "+accBalance);
	}
	public void Deposit(int balance)
	{
		accBalance=accBalance+balance;
		System.out.println("Amount Deposited. Current Balance is "+accBalance);
		creditTrans[creditCnt++]= balance;
	}
	public void checkBalance()
	{
		System.out.println("Hi "+userName+ "  your Current Updated Balance is "+accBalance);
	}
	public void changepin(int pin,int number) 
	{
		if(mobileNumber==number)
		{
			atmPin=pin;
		     System.out.println("Pin changed. You new Atm Pin is   "+atmPin);
		}
		else
		{
			System.out.println("Sorry not authorised . try again  ");
		}
	}
	public void viewProfile()
	{
		System.out.println("Name            "+this.userName);
		System.out.println("Account Number  "+this.accNo);
		System.out.println("Balance         "+this.accBalance);
		System.out.println("Bank Name       "+this.bankName);
		System.out.println("Mobile Number   "+this.mobileNumber);
		System.out.println("ATM Pin Number   "+atmPin);
	}
	public void debit( ) 
	{
		/*DateTimeFormatter date=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		int ctr=1;
		for(int val:debitTrans)
			if(val!=0)
				System.out.println((ctr++)+" | "+date.format(LocalDateTime.now())+" |  debit        | "+val);*/
		}
		
	public void credit() {
	/*	int ctr1=1;
		DateTimeFormatter date=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		for(int val1:creditTrans)
			if(val1!=0)
				System.out.println((ctr1++)+" | "+date.format(LocalDateTime.now())+" | credit      | "+val1);*/
	}
}