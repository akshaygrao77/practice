package ATM;

import java.util.HashMap;
import java.util.Scanner;

public class atmCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int amount=0,amount_avail=0,flag,exit,choice,i,temp;
		Scanner s=new Scanner(System.in);
		int[] den_values= {100,200,500,2000};
		int[] initdenominations= {0,3,0,0};
		String[] denomination_name= {"Hundred","TwoHundred","FiveHundred","TwoThousand"};
		HashMap<String,Integer> denomination_values=new HashMap(denomination_name.length);
		HashMap<String,Integer> denomination_needed=new HashMap(denomination_name.length);
		HashMap<String,Integer> denomination_remaining=new HashMap(denomination_name.length);
		int n,dlength=denomination_name.length;
		for(i=0;i<denomination_name.length;i++) //Initialize denomination numbers available
		{
			denomination_needed.put(denomination_name[i],0);
			denomination_values.put(denomination_name[i], den_values[i]);
			denomination_remaining.put(denomination_name[i],initdenominations[i]);
			amount_avail+=denomination_remaining.get(denomination_name[i])*denomination_values.get(denomination_name[i]);
		}
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
			temp=amount;
			for(i=dlength-1;i>0;i--)
			{
				if(amount>0)
				{
					if(denomination_remaining.get(denomination_name[i].toString())>0)
					{
						n=amount/denomination_values.get(denomination_name[i]);
						if(n>0)
						{
							amount=amount%denomination_values.get(denomination_name[i]);
//							amount_avail-=n*denomination_values.get(denomination_name[i]);
							denomination_remaining.put(denomination_name[i], denomination_remaining.get(denomination_name[i])-n);
						}
						denomination_needed.put(denomination_name[i], n);
					}
				}
			}
			if(amount!=0)
			{
				System.out.println("No enough denominations");
				for(i=dlength-1;i>0;i--)
				{
					denomination_remaining.put(denomination_name[i], denomination_remaining.get(denomination_name[i])+denomination_needed.get(denomination_name[i]));
				}
			}
			else
			{
				System.out.println("Denominations needed is:");
				for(i=0;i<denomination_name.length;i++)
				{
					System.out.println(""+denomination_name[i]+"'s-->"+denomination_needed.get(denomination_name[i]));
				}
				System.out.println("Amount remaining is:"+amount_avail);
				System.out.println("Denominations remaining are:");
				for(i=0;i<denomination_name.length;i++)
				{
					System.out.println(""+denomination_name[i]+"'s-->"+denomination_remaining.get(denomination_name[i]));
				}
			}
			do
			{
				System.out.println("Press 0 to exit and 1 to continue");
				choice=s.nextInt();
				exit=choice;
			}while(choice<0 || choice >1);
		}
		while(exit==1);
	}
}
