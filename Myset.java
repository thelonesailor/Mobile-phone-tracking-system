import java.util.LinkedList;
import java.util.ListIterator;


public class Myset 
{
	LinkedList<Object> ll=new LinkedList<Object>();  

	
	 public Boolean IsEmpty()  // returns true if the set is empty
	 {
		 if(ll.size()==0)
		 {return true;}
		 else
		 {return false;}
	 }
	 public int size()
	 {return ll.size();}
	 
		 public Boolean IsMember(Object o)// Returns true if o is in the set, false otherwise.
		 {
			 ListIterator<Object> it=ll.listIterator(0);
	
			 while(it.hasNext())
			 {
				 Object r=it.next();
				 if(r.equals(o))
				 {return true;}
			 }
			 
			 return false;
		  }
		 
		 public void Insert(Object o)// Inserts o into the set.
		 {
			 if(!IsMember(o))
			 {ll.add(o);}
		 }
		 
		
		 public void Delete(Object o)// Deletes o from the set, throws exception if o is not in the set.
		 {
			 ListIterator<Object> it=ll.listIterator(0);
			 while(it.hasNext())
			 {
				 Object r=it.next();
				 if(r.equals(o))
				 {ll.removeFirstOccurrence(o);
				 return;}
			 }

			 System . out . print ("ERROR- Can't delete object is not in the set ");
			 return;
		 }
		
		 public Myset Union(Myset a)// Returns a set which is the union of the current set with the set a
		 {
			 Myset newset=new Myset();
			 ListIterator<Object> it=ll.listIterator(0);
			 
			 while(it.hasNext())
			 {
				 Object u=it.next();
				 newset.Insert(u);
			 }
			 
			 ListIterator<Object> it2=a.ll.listIterator(0);
			
			 while(it2.hasNext())
			 {
				 Object u=it2.next();
				 newset.Insert(u);
			 }
			 
			 return newset;
		 }
		 public Myset Intersection(Myset a)// Returns a set which is the intersection of the current set with the set a
		 {
			 Myset newset=new Myset();
			 ListIterator<Object> it=ll.listIterator(0);
			 
			 while(it.hasNext())
			 {
				 Object u=it.next();
				 if(a.IsMember(u))  // u is in both a and current set
				 {newset.Insert(u);}
			 }
			 
			 return newset;
		 }
		
	

	
}



class MobilePhone
{

public boolean sts;
public int number;
RoutingMapTree.Exchange basestation;


MobilePhone (int num)//: constructor to create a mobile phone. Unique identifier for a mobile phone is an integer.
{number=num;sts=false;}


public int number()// returns the ID of the mobile phone.
{return number;}
public boolean status()// returns the status of the phone, i.e. switched on or switched off.
{return sts;}
public void switchOn()// Changes the status to switched on.
{sts=true;} 
public void switchOff()// Changes the status to switched off.
{sts=false;}


public RoutingMapTree.Exchange location()
// returns the base station with which the phone is registered if the phone is switched on, and an exception if the phone is off
{
	if(sts==false) // phone is switched Off
	{
		System.out.println("ERROR- Mobile Phone is switched off");
		return null;
	}
	else
	{
		return basestation;
	}
}

public void setbasestation(RoutingMapTree.Exchange o)
{basestation=o;}

}




class MobilePhoneSet
{
	Myset ms=new Myset();
	
	
	 public Boolean IsEmpty()// returns true if the set is empty.
	 {
		 if(ms.size()==0)
		 {return true;}
		 else
		 {return false;}
	 }
	 
	 
		 public Boolean IsMember(MobilePhone o)// Returns true if o is in the set, false otherwise
		 {
			 return ms.IsMember(o);
    	 }
		 
		 
		 public void Insert(MobilePhone o)// Inserts o into the set.
		 {
			 ms.Insert(o);
		 }
		 
		 
		 public void Delete(MobilePhone o)// Deletes o from the set, throws exception if o is not in the set
		 {
			 if(ms.IsMember(o)==false)
			 {System.out.println("ERROR- Can't delete, Mobile Phone is not in the set");}
			 else
			 {ms.Delete(o);}
		 }
		
		 
		 public MobilePhoneSet Union(MobilePhoneSet a)//: Returns a set which is the union of the current set with the set a.
		 {
			 MobilePhoneSet r=new MobilePhoneSet();
			 r.ms=ms.Union(a.ms);
			 return r;
		 }
		 
		 
		 public MobilePhoneSet Intersection(MobilePhoneSet a)// Returns a set which is the intersection of the current set with the set 'a'
		 {
			 MobilePhoneSet r=new MobilePhoneSet();
			 r.ms=ms.Intersection(a.ms);
			 return r;
		 }
		
}
	