import java.util.Arrays;
import java.io.*;
import java.util.*;

class GlobalR {
       public static String[] number = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
	   public static String[] number100 = {"I","IV","V","IX","X","XL","L"};
	   public static int[] number100A = {1,4,5,9,10,90,100};
}


class Number
{
	public boolean Create(String str)
	{
		char c = str.charAt(0);
		if(c >= '1' && c <= '9')
		{
			_value = Integer.parseInt(str);
			return true;
			
		}
		else
		{	
			str = str.toUpperCase();
			int index = (Arrays.asList(GlobalR.number)).indexOf(str);
			if(index >= 0)
			{
				_value = index + 1;
				_isR = true;
				return true;
			}
		}
		return false;
		
	}
	public String IntToR(int a)
	{
		String str = "";
		int indexD = 6;
		while(a != 0)
		{
			int d0 = a / GlobalR.number100A[indexD];
			if(d0 != 0)
			{				
				str += GlobalR.number100[indexD];
				a = a - GlobalR.number100A[indexD];
			}
			else 
				indexD--;
			
		}
		return str;
	}
	public String Calc(Number nb1, char typeOperation)
	{
		if(IsR() != nb1.IsR())
			throw new NullPointerException("number formats do not match");
		if(!IsR())
		{
			switch(typeOperation)
			{
			case '+':
				_value += nb1.GetValue();
				return String.valueOf(_value);
			case '-':
				 _value -= nb1.GetValue();
				return String.valueOf(_value);
			case '*':
				_value *= nb1.GetValue();
				return String.valueOf(_value);
			case '/':
				_value /= nb1.GetValue();
				return String.valueOf(_value);
			default:
				throw new NullPointerException("unknow operator");
			}
		}
		else 
			{
			switch(typeOperation)
			{
			case '+':
				_value += nb1.GetValue();
				return IntToR(_value);
			case '-':
				_value -= nb1.GetValue();
				return IntToR(_value);
			case '*':
				_value *= nb1.GetValue();
				return IntToR(_value);
			case '/':
				_value /= nb1.GetValue();
				return IntToR(_value);
			default:
				throw new NullPointerException("unknow operator");
			}
		}	
		
	}
	public int GetValue() { return _value;}
	public boolean IsR() { return _isR;}
	
	private int _value = 1000;
	private boolean _isR = false;
}

public class Calculator
{
	private static String GetNextS(StringBuffer str)
	{
		while(str.charAt(0) == ' ')
			str.deleteCharAt(0);
		char space = ' ';
		int indexSpace = str.toString().indexOf(space);
		if(indexSpace == -1)
			indexSpace = str.length();
		String res = str.toString().substring(0, indexSpace);
		str.delete(0,indexSpace + 1);
		return res;
	}
	
	public static void main(String args[]){
		while(true)
		{
			System.out.print("input : \n");
			Scanner sc = new Scanner(System.in);
			String str1 = sc.nextLine();
			str1.trim();
			StringBuffer  str = new StringBuffer(str1);
			Number firstN = new Number(), nextN = new Number();
		
			if(!(firstN.Create(GetNextS(str))))
			{
				System.out.println("nvalid input data");
				continue;
			}
			char operatorC = GetNextS(str).charAt(0);
			if(!(nextN.Create(GetNextS(str))))
			{
				System.out.println("nvalid input data");
				continue;
			}
			String result = firstN.Calc(nextN, operatorC);
			System.out.printf("%s \n",result); 
		}
   }
}