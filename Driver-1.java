import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Driver 
{
	public static void main(String args[]) throws Exception 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp","root","Dhinesh@1");
		Statement stat = con.createStatement();
		DateTimeFormatter date=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Scanner scan= new Scanner(System.in);
		System.out.println("Enter card pin");
		int enterPin=scan.nextInt();
		ResultSet res = stat.executeQuery("select * from atm_details where pin = "+enterPin);
		res.next(); 
		Atm atm=new Atm(res.getString(1),res.getInt(2),res.getInt(3),res.getInt(4),res.getInt(5),res.getString(6));
		if(atm.login(enterPin)==1)
		{
			int option;
			do {
				System.out.println("-----------------------------------------------");
				System.out.println("1.Show Profile");
				System.out.println("2.withdraw");
				System.out.println("3.Deposit");
				System.out.println("4.change pin");
				System.out.println("5.check balance");
				System.out.println("6.view last 5 debit statement");
				System.out.println("7.view last 5 credit statement");
				System.out.println("8.view Mini-statement");
				System.out.println("9.exit");	
				System.out.println("Enter your option");
				System.out.println("-----------------------------------------------");
				option=scan.nextInt();
			
			switch(option){
			case 1:
				atm.viewProfile();
				break;
			case 2:
				System.out.println("can withdraw only 1000-9999 at single time");
				System.out.println("Enter amount to withdraw");
				int withdrawAmount=scan.nextInt();
				if(withdrawAmount>=1000 && withdrawAmount<10000)
				{
					atm.withdraw(withdrawAmount);
					stat.executeUpdate("update atm_details set balance= "+atm.accBalance+" where pin = "+enterPin);
					stat.executeUpdate("insert into transactions(accnt_number,mode,amount,balance) values("+atm.accNo
										+", 'debit' ,"+withdrawAmount+","+atm.accBalance+")");
				}
				else {
					System.out.println("Please enter amount between 1000-9999");
				}
				break;
			case 3:
				System.out.println("can deposit only 1000-9999 at single time");
				System.out.println("Enter amount to Deposit");
				int depositAmount=scan.nextInt();
				if(depositAmount>=1000 && depositAmount<10000)
				{
					atm.Deposit(depositAmount);
					stat.executeUpdate("update atm_details set balance= "+atm.accBalance+" where pin = "+enterPin);
					stat.executeUpdate("insert into transactions(accnt_number,mode,amount,balance) values("+atm.accNo
							+", 'credit' ,"+depositAmount+","+atm.accBalance+")");
				}
				else {
					System.out.println("Please enter amount between 1000-9999");
				}
			
				break;
			case 4:
				System.out.println("Enter New pin to change");
				int newPin=scan.nextInt();
				System.out.println("please enter your registered mobile number for validation");
				int number=scan.nextInt();
				atm.changepin(newPin,number);
				stat.executeUpdate("update atm_details set pin= "+newPin+" where pin = "+enterPin);
				break;
			case 5:
				atm.checkBalance();
				break;
			case 6:
				//atm.debit();
				int ctr1=1;
				res = stat.executeQuery("select * from transactions where accnt_number = "+atm.accNo+" and mode = 'debit' order by date_of_transaction desc  limit 5 ");
				for(;res.next();) {
					System.out.println((ctr1++)+" | "+res.getInt(1)+" | "+res.getString(2)+" | "+res.getString(3)+" | "+res.getInt(4)+"  | "+res.getInt(5));
				}
				break;
			case 7:
				int ctr=1;
				//atm.credit();
				res = stat.executeQuery("select * from transactions where accnt_number = "+atm.accNo+" and mode = 'credit' order by date_of_transaction desc  limit 5 ");
				for(;res.next();) {
					System.out.println((ctr++)+" | "+res.getInt(1)+" | "+res.getString(2)+"  |"+res.getString(3)+" | "+res.getInt(4)+"  | "+res.getInt(5));
				}
				break;
			case 8:
				int ctr2=1;
	
				res = stat.executeQuery("select * from transactions where accnt_number = "+atm.accNo+" order by date_of_transaction desc  limit 10 ");
				for(;res.next();) {
					System.out.println((ctr2++)+" | "+res.getInt(1)+" | "+res.getString(2)+"  |"+res.getString(3)+" | "+res.getInt(4)+"  | "+res.getInt(5));
				}
				break;
			case 9:
				System.exit(0);
			default:
				System.out.println("enter option 1-8");
			}
			}while(option!=9);
		}
		else
			System.out.print("incorrect pin");
	}
}
