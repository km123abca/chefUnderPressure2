import java.io.*;
import java.util.*;
import java.lang.*;

 public class chefUnderPressure
 	{
 	public static void main(String[] args) throws IOException
  	 {
 		BufferedReader br=null;
 		FileReader fr=null;
 		FileWriter f=null;
		BufferedWriter bw=null;
 	try
     	{
 		PrintStream o = new PrintStream(new File("data\\A.txt"));
 		PrintStream console = System.out;
 		System.setOut(o);

 		fr=new FileReader("data\\inp.txt");
 		br= new BufferedReader(fr);
 		f=new FileWriter("data\\kongsout.txt") ;
 		bw=new BufferedWriter(f);
 		String n1;
 		String[] l;
 		int[][] adjmat;
 		int[] prodmat;
 		int n,k,b,a1,a2,q,livingCity,prodReq,cBuy;
  
		{   
		n1= br.readLine();
		l=n1.split("\\s");
		n=Integer.parseInt(l[0]);
		adjmat=new int[n][n];
		prodmat=new int[n];
			//initializing the adjacency matrix
		for(int i=0;i<n;i++)
			{
			for(int j=0;j<n;j++)
				{
				adjmat[i][j]=0;
				}
			}
			//initializing the product matrix
		for(int i=0;i<n;i++)
			{
			prodmat[i]=0;
			}
			k=Integer.parseInt(l[1]);
			
		//parsing line2 
		n1= br.readLine();
		l=n1.split("\\s");
		b=Integer.parseInt(l[0]);
			
		//parsing subsequent n-1 lines
		for (int i=0;i<(n-1);i++)
			{
			n1=br.readLine();
			l=n1.split("\\s");
			a1=Integer.parseInt(l[0]);
			a2=Integer.parseInt(l[1]);
			adjmat[a1-1][a2-1]=1;
			adjmat[a2-1][a1-1]=1;
			}
			
		//parsing the next n lines to assign products
		 for (int i=0;i<n;i++)
		    {
			n1=br.readLine();
			l=n1.split("\\s");
			prodmat[i]=Integer.parseInt(l[0]);
			}
			
		//parsing the next line to extract the number  of queries
		  n1=br.readLine();
		  l=n1.split("\\s");
		  q=Integer.parseInt(l[0]);
			//
			System.out.println("No of cities:"+n);
			System.out.println("No of products:"+k);
			System.out.println("city in which the king is staying:" +b);
			System.out.println("adjacency matrix is as follows");
			printmat(adjmat);
			System.out.println("product array is as follows");
			System.out.println(Arrays.toString(prodmat));
			
			/*
			int[] testDist=clos(-1,4,adjmat,0,1,0);  //1prev,2cur,3mat,4len,5dest,6dep
			System.out.println("shortest distance expected was 2 and what we got is:"+testDist[0]);
			 testing the closest distance function here
			*/
		
		//parsing the next q lines to process the q queries
		    for (int i=0;i<q;i++){
			n1=br.readLine();
			l=n1.split("\\s");
			livingCity=Integer.parseInt(l[0]);
			prodReq=Integer.parseInt(l[1]);
			
			//int processs(int livingCity,int prodReq,int[] prodmat,int[][] adjmat,int b,int n,int k)
			System.out.println("living city:"+(livingCity));
			System.out.println("required product:"+prodReq);
			cBuy=processs(livingCity,prodReq,prodmat,adjmat,b,n,k);
			System.out.println("get it from:"+(cBuy+1));
			//bw.write(Integer.toString(cBuy));
			//bw.newLine();
			}
		   
		    
			
		
			
			
			 
		}
		System.setOut(console);
		System.out.println("reassigned");
      }
 finally
     {
     	bw.close();
 		f.close();
 		fr.close();
 		br.close();
 		
     }
   }
   public static void printmat(int[][] mat)
	  {int m,n; System.out.print('\n');
	   m=mat.length;
	   n=mat[0].length;
		  for(int i=0;i<m;i++)
          { for(int j=0;j<n;j++)
		   { System.out.print(mat[i][j]);
		     System.out.print(' ');	
		   }System.out.println(' ');
		
          }
		  
	  }
	  
	  //4-10-2017
	  //This is a function to find out the closest distance to the destination  from
	  //each point cur_city in the adjacency matrix adjmat
	public static int[] clos(int prev_city,int cur_city,int[][] adjmat,int l,int dest,int depth)
	  {
		int n,candidateLength,testLen;
		boolean rdflg;
		int[] retval,retmat;
		retval=new int[2];
		retmat=new int[2];
		n=adjmat.length;
		retval[0]=l;
		retval[1]=0;
		depth+=1;
		if (depth>100) 
			{
			System.out.println("crash at depth="+depth);
			return retval;
			}
		if (cur_city==dest)
		  {
		  retval[1]=1;
		  //retval=appendd(retval,cur_city);
		  return retval;
		  }
		testLen=9999; 
		rdflg=true;
		for (int j=0;j<n;j++)
		  {
		   if ( (j==prev_city)||(j==cur_city)) continue;
		   if (adjmat[cur_city][j]==1) 
		      {rdflg=false;
			  retmat=clos(cur_city,j,adjmat,l+1,dest,depth);
			  candidateLength=retmat[0];
			  if ((candidateLength<testLen)&&(retmat[1]==1)) testLen=candidateLength;
			  }
		  }
		 if(rdflg) 
			{
			retmat[0]=9999;
			retmat[1]=0;
			}
		 else
		    {
			retmat[0]=testLen;
			retmat[1]=1;
			}
		return retmat;	
		  
	  }
	  
	  
	  //start of function 2
	  public static int[] closq(int prev_city,int cur_city,int[][] adjmat,int l,int dest,int b,int depth)
	  {
		int n,candidateLength,testLen;
		boolean rdflg;
		int[] retval,retmat,retmatcp;
		retval=new int[3];
		retmat=new int[3];
		retmatcp=new int[3];
		n=adjmat.length;
		retval[0]=l;
		retval[1]=0;
		retval[2]=9999;
		depth+=1;
		if (depth>100) 
			{
			System.out.println("crash at depth="+depth);
			return retval;
			}
		if (cur_city==dest)
		  {
		  retval[1]=1;
		  if (clos(-1,dest,adjmat,0,b,0)[1]==1)
		  retval[2]=clos(-1,dest,adjmat,0,b,0)[0];  //1prev,2cur,3mat,4len,5dest,6dep
		  //retval=appendd(retval,cur_city);
		  return retval;
		  }
		testLen=9999; 
		rdflg=true;
		int[] rematcp=new int[3];
		for (int j=0;j<n;j++)
		  {
		   if ( (j==prev_city)||(j==cur_city)) continue;
		   if (adjmat[cur_city][j]==1) 
		      {rdflg=false;
			  retmat=closq(cur_city,j,adjmat,l+1,dest,b,depth);
			  candidateLength=retmat[0];
			  if ((candidateLength<testLen)&&(retmat[1]==1)) 
			      {
				  testLen=candidateLength;
				  System.arraycopy(retmat,0,retmatcp,0,3);
				   
				     
				  }
			  }
		  }
		 if(rdflg) 
			{
			retmat[0]=9999;
			retmat[1]=0;
			retmat[2]=0;
			}
		 else
		    {
			//retmat[0]=testLen;
			//retmat[1]=1;
			System.arraycopy(retmatcp,0,retmat,0,3);
			if( (clos(-1,cur_city,adjmat,0,b,0)[0]<retmat[2]) &&(l!=0) && ( clos(-1,cur_city,adjmat,0,b,0)[1]==1 ) )
				retmat[2]= clos(-1,cur_city,adjmat,0,b,0)[0];
			}
		return retmat;	
		  
	  }
	  // end of function 2
	//closq(int prev_city,int cur_city,int[][] adjmat,int l,int dest,int b,int depth)  
	public static int processs(int livingCity,int prodReq,int[] prodmat,int[][] adjmat,int b,int n,int k)
		{
		//core function starts here
		int lcity,prod,shrtElem,lngstpath,lnelem;
		lcity=livingCity-1;b-=1;
		prod=prodReq;	
		shrtElem=9999;
		lngstpath=0;
		lnelem=lcity;
		int[] resmat=new int[3];
		for (int i=0;i<prodmat.length;i++)
			{ //System.out.println("searching for "+prod);
				if (prodmat[i]==prod)
					{
					 //System.out.println("product "+prod+" is available at city "+(i+1));
					 resmat=closq(-1,lcity,adjmat,0,i,b,0);
					 //System.out.println(Arrays.toString(resmat));

					 if (i<shrtElem) shrtElem=i;
					 if (resmat[2]>lngstpath) 
					 	{
					 		lngstpath=resmat[2];
					 		lnelem=i;
					 	}
					}
			}
		if (lngstpath==0) return shrtElem;
		return lnelem;
		
		}
	
	public static int[] appendd(int[] a,int ele)
		{
		int n=a.length;
		int[] b=new int[n+1];
		System.arraycopy(a,0,b,0,n);
		b[n]=ele;
		return b;
		}
		
		
	
	
 }