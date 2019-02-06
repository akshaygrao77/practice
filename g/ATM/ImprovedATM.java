package ATM;

import java.util.Collections;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class ImprovedATM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int amount=0,amount_avail=0,exit,choice,i,temp,n;
		Scanner s=new Scanner(System.in);
		TreeMap<Integer,Integer> den=new TreeMap(Collections.reverseOrder());
		int[] den_needed= {0,0,0,0};
		den.put(100, 0);
		den.put(200, 3);
		den.put(500, 0);
		den.put(2000,0);
		Set<Entry<Integer, Integer>> entires = den.entrySet();
        for(Entry<Integer, Integer> ent:entires){
//            System.out.println(ent.getKey()+" ==> "+ent.getValue());
        	amount_avail+=ent.getKey()*ent.getValue();
        }
		do
		{
			while(true)
			{
				System.out.println("Enter Amount");
				
				amount=s.nextInt();
				if(amount>amount_avail)
				{
					System.out.println("No sufficient funds in ATM");
					System.exit(0);
				}
				else if(amount%100!=0)
				{
					System.out.println("Enter amount in multiples of 100");
				}
				else
				{
					break;
				}
			}
			temp=amount;
			i=0;
			Set<Entry<Integer, Integer>> entry = den.entrySet();
	        for(Entry<Integer, Integer> ent:entry){
	        	if(amount>0 && ent.getValue()>0)
	        	{
	        		n=amount/ent.getKey();
	        		if(n>0)
	        		{
	        			den.put(ent.getKey(),ent.getValue()-n);
	        			amount=amount%ent.getKey();
	        		}
	        		den_needed[i++]=n;
	        	}
	        }
			if(amount==0)
			{
				i=0;
				Set<Entry<Integer, Integer>> en = den.entrySet();
				for(Entry<Integer, Integer> ent:en) {
					System.out.println(ent.getKey()+" ==> "+den_needed[i++]);
		        }
				amount_avail-=temp;
				System.out.println("Amount remaining is:"+amount_avail);
				System.out.println("Remaining denominations are:");
				Set<Entry<Integer, Integer>> e = den.entrySet();
				for(Entry<Integer, Integer> ent:e) {
					System.out.println(ent.getKey()+" ==> "+ent.getValue());
		        }
			}
			else
			{
				System.out.println("No enough Denominations");
				i=0;
				Set<Entry<Integer, Integer>> entr = den.entrySet();
				for(Entry<Integer, Integer> ent:entr) {
					den.put(ent.getKey(),ent.getValue()+den_needed[i++]);
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
