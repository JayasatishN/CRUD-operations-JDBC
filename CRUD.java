package jdbc_app;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class CRUD {

	static Connection con;
	static PreparedStatement ps;
	static ResultSet rs;
	static String q,sq,sr[];
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	public static void create() throws Exception
	{
		System.out.print("Enter no.of columns: ");
		int a=Integer.parseInt(br.readLine());
		q="insert into Registration (";
		sq=") values (";
		sr=new String[a];
		for(int i=0;i<a;i++)
		{
			System.out.print("Enter "+(i+1)+" column name: ");
			String ch=br.readLine();
			sr[i]=ch;
			q+=ch;sq+="?";
			if(i+1<a)
			{
				q+=",";
				sq+=",";
			}
		}
		q+=sq+")";
		ps=con.prepareStatement(q);
		for(int i=1;i<=a;i++)
		{
			System.out.print("Enter "+sr[i-1]+" column value: ");
			ps.setString(i, br.readLine());
		}
		ps.execute();
		System.out.println("Inserted Successully!...\n");
	}
	public static void read()  throws Exception
	{
		q="select * from Registration";
		ps=con.prepareStatement(q);
		rs=ps.executeQuery();
		while(rs.next())
			System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
		System.out.println("Reading records completed!.. \n");
	}
	public static void update()  throws Exception
	{
		System.out.print("Enter no.of columns: ");
		q="update Registration set ";
		int a=Integer.parseInt(br.readLine());
		if (a==0)return ;
		sr=new String[a];
		for(int i=0;i<a;i++)
		{
			System.out.print("Enter "+(i+1)+" column name: ");
			String ch=br.readLine();
			sr[i]=ch;
			q+=ch+"=?";
			if(i+1<a)
				q+=",";
		}
		System.out.print("Enter condition column name: ");
		String c1=br.readLine();
		q+=" where "+c1+"=?";
		System.out.println(q);
		ps=con.prepareStatement(q);
		for(int i=1;i<=a;i++)
		{
			System.out.println("Enter "+sr[i-1]+" value: ");
			ps.setString(i, br.readLine());
		}
		System.out.println("Enter "+c1+" value: ");
		ps.setString(a+1, br.readLine());
		ps.execute();
		System.out.println("Record Updated Successully!...\n");
	}
	public static void delete()  throws Exception
	{
		q="delete from Registration where ";
		System.out.print("Enter condition column name: ");
		sq=br.readLine();
		System.out.print("Enter "+sq+" column value: ");
		String z=br.readLine();
		if(z.equals("NULL"))
			q+=sq+" IS NULL";
		else
			q+=sq+"=?";
		ps=con.prepareStatement(q);
		System.out.println(q);
		if(!z.equals("NULL"))
		ps.setString(1, z);
		ps.execute();
		System.out.println("Record deleted Successully!...\n");
	}
	public static void main(String args[])
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hello","root", "Satish@123");

			while(true) 
			{
				System.out.println("Enter number according to the operations below \n1.Create \n2.Read \n3.Update \n4.Delete \n5.Exit");
				int choice=Integer.parseInt(br.readLine());
				switch(choice)
				{
					case 1:
						create();
						break;
					case 2:
						read();
						break;
					case 3:
						update();
						break;
					case 4:
						delete();
						break;
					case 5:
						System.out.println("Exited Successfully :))");
						rs.close();
						ps.close();
						con.close();
						return ;
					default:
						System.out.println("please enter valid option: ");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}