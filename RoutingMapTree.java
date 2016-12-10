import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class RoutingMapTree{

	Exchange root;
	public RoutingMapTree() 
	{
	   root=new Exchange(0);
	   root.setparent(null);
	   root.base=true;
	   allex.add(root);
	   allexchanges.Insert(root);
	}
    
	public RoutingMapTree(int a) 
	{
	   Exchange root=new Exchange(a);
	   root.setparent(null);
	   root.base=true;
	   allex.add(root);
	   allexchanges.Insert(root);
	}

	
	public void switchOn(MobilePhone a, Exchange b)
 // It switches the phone a On and registers it with base station b and updates routing map tree
	{
		
		a.switchOn(); // sets status=true
		a.setbasestation(b);
		allph.add(a);
		allphones.Insert(a);
		
		    Exchange temp=b;		 
			while(temp!=null)
			{
			temp.allm.add(a);
		//	temp.base=false;
			temp.mobilephoneset.Insert(a);
			temp=temp.parent();
			}
		//	b.base=true;		
	}
	
	
	public void switchOff(MobilePhone a)
	/*  Works on mobile phones that are currently switched on.
	    The entire routing map tree is updated accordingly.*/
	{
		
		a.switchOff();// changes the status to switched Off
		allph.remove(a);
		Exchange temp=find(root,a);
		
		
		
		while(temp!=null)
		{
			if(temp.mobilephoneset.IsMember(a))
			{temp.allm.remove(a);
			temp.mobilephoneset.Delete(a);
			}
			else
			{System.out.print(actionmessage+": ");
			 System.out.println("ERROR- Can't delete, Mobile Phone is already switched Off");
			break;}
			temp=temp.parent;
		}
		
	}
	 		
	
	
	String actionmessage="";	
	public void performAction(String actionMessage) {
		
		Scanner s=new Scanner (actionMessage);
		
			
		String a="",b="";
		actionmessage=actionMessage;
		int a1,b1;
		String query_type="";
		if(s.hasNext())
		{query_type=s.next();}
		
		
		
		if(query_type.equals("addExchange"))//to exchange a1 add b1
		{
			if(s.hasNext())
			{a=s.next();}
			if(s.hasNext())
			{b=s.next();}
			a1 = Integer.parseInt(a);  
			b1 = Integer.parseInt(b);
			
			
			ListIterator<Exchange> it=allex.listIterator(); 
		    Exchange temp=null;
			if(allex.size()>0)
			{
		
			temp=null;
			int u=0;
			while(it.hasNext())
			{	
		
				temp=it.next();
				if(temp.exnum==a1)
				{
					u=1;
					break;
				}
			
			}

	        if(u==0)
            {System.out.println(actionMessage+": ERROR- There is no exchange with identifier "+a1);
            return;}
	        
	        	Exchange temp2=null;
				u=0;
				while(it.hasNext())
				{	
					//temp2=allex.get(i);
					temp2=it.next();
	              
					if(temp2.exnum==b1)
					{
						u=1;
						break;
					}
				
				}
			
				
		    if(u==1)
			{System.out.println(actionMessage+": ERROR- Can't create new exchange, There is already an exchange with identifier "+b1);
			return;}
			
	        if(temp!=null)
			{
	        
		    Exchange newex=new Exchange(b1);
			newex.setparent(temp);
			temp.base=false;
			temp.ChildExchanges.Insert(newex);     
			temp.childrenexchanges.add(newex);     // add new exchange to the child list of parent Exchange 
			allex.add(newex); //TODO  
			}
	        }
			else
			{System.out.println(actionMessage+": ERROR- No exchange present");}
	        
		
		
		}
		

		else if(query_type.equals("queryNthChild")) // print bth child of exchange with identifier a
		{
			if(s.hasNext())
			{a=s.next();}
			if(s.hasNext())
			{b=s.next();}
			a1 = Integer.parseInt(a);
			b1 = Integer.parseInt(b);
			
			
			ListIterator<Exchange> it=allex.listIterator();
			
			Exchange temp=null;
			
			int i=0,u=0;
			while(it.hasNext())
			{
			
				temp=it.next();
				++i;
				if(temp.exnum==a1)
				{
					u=1;
					break; 
				}
			}
			
			
			System.out.print(actionMessage+": ");
			if(u==0)
			{System.out.println("ERROR- there is no exchange with identifier "+a1);
			return;}
			
				
			Exchange r=temp.child(b1);
			if(r!=null)
			{System.out.println(r.exnum);}
			
		}
		
		
		else if(query_type.equals("queryMobilePhoneSet"))
		{
			if(s.hasNext())
			{a=s.next();}
			a1 = Integer.parseInt(a);

			
			ListIterator<Exchange> it=allex.listIterator();
			Exchange temp=null;
			int i=0,u=0;
			while(it.hasNext())
			{
				temp=it.next();
				++i;
				if(temp.exnum==a1)
				{u=1;
					break;
				}
			}
			
	        System.out.print(actionMessage+": ");
			
	        if(u==0)
			{System.out.println("ERROR- No Exchange with identifier "+a1);
			return;}
			if(temp.allm.size()==0)
			{System.out.println("ERROR- No mobile phone present in the resident set of exchange");
			return;}
			
			
			 ListIterator<MobilePhone> it2=temp.allm.listIterator();
			 MobilePhone r=null;
			 
			 while(it2.hasNext())
			 {
				 r=it2.next();
				 System.out.print(+r.number());
				 if(it2.hasNext())
				 {System.out.print(", ");}
			 }
			 System.out.println();
		}
		
		
		else if(query_type.equals("switchOffMobile")) // switch Off mobile with identifier  a
		{
			if(s.hasNext())
			{a=s.next();}
			a1 = Integer.parseInt(a);
			
			 ListIterator<MobilePhone> it=allph.listIterator();
			 MobilePhone temp=null;
			 int i=0,u=0;	
			 while(it.hasNext())
				{
					temp=it.next();
					++i;
					if(temp.number==a1)
					{
						u=1;
						break;
					}
				}
			 
			 
			    if(u==0)
			    {System.out.print(actionMessage+": ");
			    System.out.println("ERROR- Mobile phone is already switched Off");
			    return;}
			    
		
			    switchOff(temp);
		}
		
		
		else if(query_type.equals("switchOnMobile"))//switches ON the mobile phone a at Exchange b
		{
			if(s.hasNext())
			{a=s.next();}
			if(s.hasNext())
			{b=s.next();}
			a1 = Integer.parseInt(a);
			b1 = Integer.parseInt(b);
			
			
			ListIterator<MobilePhone> it2=allph.listIterator();
			int u=0;
			MobilePhone temp2=null;
			while(it2.hasNext())
			{
			
				temp2=it2.next();
				
				if(temp2.number==a1)
				{
                    u=1;
					break;
				}
			}
			
		    
			ListIterator<Exchange> it=allex.listIterator();
			int u2=0;
			Exchange temp=null;
			while(it.hasNext())
			{
				
				temp=it.next();
			
				if(temp.exnum==b1)
				{
					u2=1;
					break;
				}
			}
			
			
			if(u2==0)  // exchange with identifier b is not there
			{System.out.println(actionMessage+": ERROR- There is no Exchange with identifier "+b1);
			return;}	
			else if(u==1)  //mobile found switched On
			{temp2.sts=true;
			System.out.println(actionMessage+": ERROR- Mobile Phone "+temp2.number+" is already switched On ");
			return;}
			else if(temp.base==false)
			{System.out.println(actionMessage+": ERROR- Exchange "+temp.exnum+" is not a base station ");}
			else if(u2==1)
			{newmobile=new MobilePhone(a1);
			switchOn(newmobile,temp);}
		
			
		}
		
		
		else if(query_type.equals("queryFindPhone"))//This should print the identifier of the exchange returned by the findPhone(MobilePhone m) method
		{
			if(s.hasNext())
			{a=s.next();}
			a1 = Integer.parseInt(a);
			
			ListIterator<MobilePhone> it2=allph.listIterator();
			int u=0;
			MobilePhone temp2=null;
			while(it2.hasNext())
			{
			
				temp2=it2.next();
				
				if(temp2.number==a1)
				{
                    u=1;
					break;
				}
			}
			if(u==0)
			{System.out.println(actionMessage+": ERROR- mobile not found or switched off");
			return;}
			
			Exchange r=findPhone(temp2);
			if(r!=null)
			{System.out.println(actionMessage+": "+r.exnum);}
			
			
		}
		
		
		else if(query_type.equals("queryLowestRouter"))//This should print the identifier of the exchange returned by the lowestRouter(Exchange e1, Exchange e2) method
		//	Here, e1 and e2 represent exchanges with identifier a and b respectively.
		{
	
			if(s.hasNext())
			{a=s.next();}
			if(s.hasNext())
			{b=s.next();}
			a1 = Integer.parseInt(a);
			b1 = Integer.parseInt(b);
			
			ListIterator<Exchange> it=allex.listIterator(0);
			int u=0,u2=0;
			Exchange ea=null,eb=null,temp=null;
			while(it.hasNext())
			{
				temp=it.next();
				if(temp.exnum==a1 && u==0)
				{ea=temp;
				u=1;}
				if(temp.exnum==b1 && u2==0)
				{eb=temp;
				u2=1;}
				if(ea!=null && eb!=null)
				{break;}
			}
			
			Exchange r=null;
			
			if(ea!=null && eb!=null)
			{r=lowestRouter(ea,eb);}
			else
			{if(u==0)
			{System.out.println(actionMessage+": ERROR- Exchange with identifier "+a1+" not found");}
			else if(u2==0)
			{System.out.println(actionMessage+": ERROR- Exchange with identifier "+b1+" not found");}
			return;
			}
			
			if(r!=null)
			{System.out.println(actionMessage+": "+r.exnum);}
		
		}
		
		
		else if(query_type.equals("queryFindCallPath"))  // prints the list returned by the routeCall(MobilePhone m1, MobilePhone m2) 
		{
			if(s.hasNext())
			{a=s.next();}
			if(s.hasNext())
			{b=s.next();}
			a1 = Integer.parseInt(a);
			b1 = Integer.parseInt(b);
			
			ListIterator<MobilePhone> it=allph.listIterator(0);
			int u=0,u2=0;
			MobilePhone ma=null,mb=null,temp=null;
			while(it.hasNext())
			{
				temp=it.next();
				if(temp.number==a1)
				{ma=temp;
				u=1;}
				if(temp.number==b1)
				{mb=temp;
				u2=1;}
				if(ma!=null && mb!=null)
				{break;}
			}
		    
			if(u==1 && u2==1)
			{Exchangelist r=routeCall(ma,mb);
			System.out.print(actionMessage+": ");
			r.print();
			System.out.println();}
			else
			{
				int c=0;
				if(u==0)
				{c=a1;}
				else if(u2==0)
				{c=b1;}
				System.out.println(actionMessage+": ERROR- mobile phone with identifier "+c+" is switched Off");
			}
			
		}
		
		
		else if(query_type.equals("movePhone"))// sets the level 0 area exchange of the mobile phone 'a' to exchange 'b'
//			Throw exception if mobile a is not available, or exchange b does not exist
		{
			if(s.hasNext())
			{a=s.next();}
			if(s.hasNext())
			{b=s.next();}
			a1 = Integer.parseInt(a);
			b1 = Integer.parseInt(b);
			
			ListIterator<Exchange> it=allex.listIterator(0);
			int u=0,u2=0;
			Exchange ea=null,eb=null,temp=null;
			while(it.hasNext())
			{
				temp=it.next();
				if(temp.exnum==b1)
				{u2=1;
				break;}
			}
			
			ListIterator<MobilePhone> it2=allph.listIterator(0);
			MobilePhone temp2=null;
			while(it2.hasNext())
			{
				temp2=it2.next();				
				if(temp2.number==a1)
				{u=1;
				break;}
			}
			
			if(temp.base==false)// not a base station
			{System.out.println(actionMessage+": ERROR- exchange with identifier "+b1+" is not a base station");
			return;}
			
			else if(u==1 && u2==1)
			{movePhone(temp2,temp);}
			else if(u==0)
			{System.out.println(actionMessage+": ERROR- mobile phone with identifier "+a1+" is not available or switched Off");}
			else if(u2==0)
			{System.out.println(actionMessage+": ERROR- exchange with identifier "+b1+" not found");}
			
		}
	
		
	
		
		s.close();
		
	}
	
	
	Exchange findPhone(MobilePhone a)
	{
		
		Exchange ex=root;
		
		if(ex.mobilephoneset.IsMember(a)==false) 
		{return null;}
		
		if(ex.mobilephoneset.IsMember(a)==true && ex.childrenexchanges.size()>0)
		{
			ListIterator<Exchange> it=ex.childrenexchanges.listIterator(0);
			while(it.hasNext())
			{
				Exchange r=it.next();
				
				if(r.mobilephoneset.IsMember(a)==true)
				{return find(r,a);}
			}
			return null;
		}
		else if(ex.mobilephoneset.IsMember(a)==true && ex.childrenexchanges.size()==0)// exchange is base station 
		{
			return ex;
		}
		else
		{return null;}
	}
	Exchange find(Exchange ex,MobilePhone a)
	{
		if(ex.mobilephoneset.IsMember(a)==true && ex.childrenexchanges.size()>0)
		{
			ListIterator<Exchange> it=ex.childrenexchanges.listIterator();
			while(it.hasNext())
			{
				Exchange r=it.next();
				
				if(r.mobilephoneset.IsMember(a)==true)
				{return find(r,a);}
			}
			return null;
		}
		else if(ex.mobilephoneset.IsMember(a)==true && ex.childrenexchanges.size()==0)// exchange is base station 
		{
			return ex;
		}
		else
		{return null;}
	}
	
	
	
	public Exchange lowestRouter(Exchange a, Exchange b)
	//two level 0 area exchanges a and b this method returns the level i exchange with the smallest
	//possible value of i which contains both a and b in its subtree. 
	
	{
		if(a.equals(b))
		{return a;}
		Exchange ta=a,tb=b;
	    
		Exchangelist la=new Exchangelist(),lb=new Exchangelist();
		while(ta!=null)
	    {
			la.Insert(ta);
			ta=ta.parent;
	    }
		while(tb!=null)
	    {
			lb.Insert(tb);
			if(la.ismember(tb)==true)
			{return tb;}
			tb=tb.parent;
	    }
		
		Exchange r=null;
		return r;
	}
	
	
	public Exchangelist routeCall(MobilePhone a, MobilePhone b)
	/*This method helps initiate a call from phone a to phone b. It returns
	a list of exchanges from a to b */
	{
		
		Exchangelist res=new Exchangelist(),res2=new Exchangelist();
		Exchange r=lowestRouter(a.basestation,b.basestation);
		Exchange temp=null;
		
		temp=a.basestation;
		while(temp!=r)
		{
			res.Insert(temp);
			temp=temp.parent;
		}
		res.Insert(r);
		
		
		temp=b.basestation;
		while(temp!=r)
		{
			res2.Insert(temp);
			temp=temp.parent;
		}
		
		Exchangelist r2=res2.returnreverse();
		if(r2.ll.size()>0)
		{
		ListIterator<Exchange> it=r2.ll.listIterator(0);
		while(it.hasNext())
		{
			res.Insert(it.next());
		}
		}
		return res;
	}
	
	public void movePhone(MobilePhone a, Exchange b)//moves a to exchange b
	{
		if(a.sts==false)
		{System.out.println(actionmessage+": ERROR- mobile phone with identifier "+a.number+" is switched Off");
		return;}
		
		Exchange temp=a.basestation;
		while(temp!=null)
		{
			temp.allm.remove(a);
			temp.mobilephoneset.Delete(a);
			
			temp=temp.parent;
		}
		
		a.setbasestation(b);
		temp=b;
		while(temp!=null)
		{
			temp.allm.add(a);
			temp.mobilephoneset.Insert(a);
			
			temp=temp.parent;
		}
		
	}
	
	
	
	class Exchangelist
	{
		LinkedList<Exchange> ll=new LinkedList<Exchange>();
		
	    

		public void Delete(Exchange o)
		{
			if(ismember(o)==true)
			{
			ListIterator<Exchange> it=ll.listIterator();
			Exchange r;
			while(it.hasNext())
			{
				r=it.next();
				if(r.equals(o))
				{ll.remove(o);}
			}
			}
			else
			{System.out.println("ERROR- No mobile phone present in the resident set of exchange");}
		}
		
		
		public boolean ismember (Exchange o)
		{
			ListIterator<Exchange> it=ll.listIterator();
			Exchange r;
			while(it.hasNext())
			{
				r=it.next();
				if(r.equals(o))
				{return true;}
			}
			
			return false;
		}
		
		public Exchangelist returnreverse()
		{
			
			Exchangelist r=new Exchangelist();
			
			if(ll.size()<=0)
			{return r;}
			ListIterator<Exchange> it=ll.listIterator(ll.size());
		
			while(it.hasPrevious())
			{
				r.Insert(it.previous());
			}
			
			return r;
		}
		
		public void Insert(Exchange o)
		{if(ismember(o)==false)
		{ll.add(o);}
		}
		
		
		public void print()
		{
			if(ll.size()<=0)
			{return;}		
		
			ListIterator<Exchange> it=ll.listIterator(0);
			Exchange r=null;
			while(it.hasNext())
			{
				r=it.next();
				System.out.print(r.exnum);
				if(it.hasNext())
				{System.out.print(", ");}
			}
		}
		
		
		public int size()
		{return ll.size();}
		
	}
	
	
	
	
   class Exchange
	{
	
		int exnum;
		boolean base;
		Exchange parent;
		Exchangelist ChildExchanges=new Exchangelist();  //list of all children exchanges
		MobilePhoneSet mobilephoneset=new MobilePhoneSet();  //Resident set 
		

		 
	 Exchange(int number)// constructor to create an exchange. Unique identifier for an exchange is an integer.
	 {exnum=number;
	 parent=null;
	 base=true;}
	
			 
	 public void setparent(Exchange p) 
	 {parent=p;} 
	 
	 public Exchange parent()  //returns the parent of exchange
	 {return parent;}
	 
	 public int numChildren()  //returns number of children of exchange
	 {return childrenexchanges.size();}
	 
	 
		public void printallmobiles()
		{
			if(allm.size()==0)
			{System.out.println("ERROR- No mobile phone present in the resident set of exchange");
			return;}
			
			 ListIterator<MobilePhone> it=allm.listIterator();
			 MobilePhone r=null;
			 int i=0;
			 while(it.hasNext())
			 {
				 r=it.next();
				 ++i;
				 System.out.print(r.number()+" ");
			 }
			 System.out.println();
		}
		
		
	 public Exchange child(int i)  //returns the ith child of the exchange, i=0,1,2....,size-1
	 {
		 
		 Exchange r=null;
	
		 if(i>=childrenexchanges.size())
		 {
			 System.out.println("ERROR- Exchange "+exnum+" does not have "+i+"th child");
			 return null;
		 }
		 if(childrenexchanges.size()>0)
		 {
			 
		 ListIterator<Exchange> it=childrenexchanges.listIterator();			 	 
		 int temp=0;	
		
		 while(it.hasNext())
		 {
			 r=childrenexchanges.get(temp);
			 it.next();
			
			 if(temp==i) // 0 based indexing
			 {break;}
			 
			 ++temp;
		 }
		
		 }
		 else
		 {System.out.println("ERROR- Exchange has no children");}

		 return r;
	 }
	 LinkedList<Exchange> childrenexchanges=new LinkedList<Exchange>(); 
	 LinkedList<MobilePhone> allm=new LinkedList<MobilePhone>();
	 public Boolean isRoot()  // returns whether Exchange is root or not
	 {return (parent==null);}

	
	 public RoutingMapTree subtree(int i)// TODO returns the ith subtree
	 {
	        Exchange temp=child(i);
	        RoutingMapTree newtree=new RoutingMapTree(temp.exnum);	        
	        newtree.allph=temp.allm;
	        newtree.allex=temp.childrenexchanges;
	        
	        return newtree;
	 }

	 
	 public MobilePhoneSet residentSet()// This returns the resident set of mobile phones of the exchange.
	 {
        return mobilephoneset;
	 }
	 
	 public void addphonetoexchange(MobilePhone o)
	 {
		 allm.add(o);
		 mobilephoneset.Insert(o);
	 }
	 
	 
    }
	
   MobilePhone newmobile;	
   LinkedList<Exchange> allex=new LinkedList<Exchange>();
   Exchangelist allexchanges=new Exchangelist();
   LinkedList<MobilePhone> allph=new LinkedList<MobilePhone>(); 
   MobilePhoneSet allphones=new MobilePhoneSet();

	
}
