package ies.jandula.launch;

import ies.jandula.method.Method;

public class Main 
{

	public static void main(String[] args) 
	{
		Method method = new Method();
		
		method.borrarPDF("src/main/resources/carpetas", "todos");

	}

}
