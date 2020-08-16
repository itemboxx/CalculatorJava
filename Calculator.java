import java.util.Arrays;
import java.io.*;
import java.util.*;

class GlobalR {
       public static String[] number = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
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
	public int Calc(Number nb1, char typeOperation)
	{
		if(IsR() != nb1.IsR())
			throw new NullPointerException("number formats do not match");
		switch(typeOperation)
		{
		case '+':
			return _value += nb1.GetValue();
		case '-':
			return _value -= nb1.GetValue();
		case '*':
			return _value *= nb1.GetValue();
		case '/':
			return _value /= nb1.GetValue();
		default:
			throw new NullPointerException("unknow operator");
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
			int result = firstN.Calc(nextN, operatorC);
			System.out.printf("%s = %d \n", str1, result); 
		}
   }
}