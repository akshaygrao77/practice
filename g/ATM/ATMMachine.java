package ATM;

import java.util.Scanner;

public class ATMMachine {
	static int[] den_avail= {0,3,0,0};
	static int[] den_num= {0,0,0,0};
	static int[] den_val= {100,200,500,2000};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int amount,exit=1,choice,flag=0,i=0;
		int amount_avail=0;
		amount_avail=computeavail();
		System.out.print("Avail"+amount_avail);
		Scanner s=new Scanner(System.in);
		do
		{
			do
			{
				System.out.println("Enter Amount");
				
				amount=s.nextInt();
				if(amount>amount_avail)
				{
					System.out.println("No sufficient funds in ATM");
					flag=1;
					System.exit(0);
				}
				else if(amount%100!=0)
				{
					System.out.println("Enter amount in multiples of 100");
					flag=1;
				}
				else
				{
					flag=0;
				}
			}
			while(flag==1);
			
			compute(amount);
			System.out.println("Denomainations needed are:");
			for(int j=0;j<4;j++)
			{
				System.out.println(""+den_val[j]+"'s-->"+den_num[j]);
			}
			System.out.println("Denominations remaining is");
			for(int j=0;j<4;j++)
			{
				System.out.println(""+den_val[j]+"'s-->"+den_avail[j]);
			}
			reducedenom();
			amount_avail=computeavail();
			
			System.out.println("Amount remaining is:"+amount_avail);
			do
			{
				System.out.println("Press 0 to exit and 1 to continue");
				choice=s.nextInt();
				exit=choice;
			}while(choice<0 || choice >1);
			
		}
		while(exit==1);
		
		
	}
	public static void reducedenom()
	{
		for(int i=0;i<4;i++)
		{
			den_num[i]=0;
		}
		
	}
	public static int computeavail()
	{ 
		int a=0;
		for(int j=0;j<4;j++)
		{
			a+=(den_avail[j]*den_val[j]);
		}
		return a;
	}
	public static int compute(int balance)
	{
		int n=0;
		for(int den_no=3;den_no>=0 && balance>0;den_no--)
		{
			if((balance>den_val[den_no]))
			{
				if((den_avail[den_no]>0))
				{
					n=computemaxmultiple(balance,den_val[den_no]);
					den_num[den_no]=n;
					den_avail[den_no]=den_avail[den_no]-n;
					balance=balance-n*den_val[den_no];
				}
			}
		}
		return 0;
	}
	public static int computemaxmultiple(int num1,int num2)
	{
		int i=0;
		while(num1>=num2)
		{
			num1=num1-num2;
			i++;
		}
		return i;
	}
}
