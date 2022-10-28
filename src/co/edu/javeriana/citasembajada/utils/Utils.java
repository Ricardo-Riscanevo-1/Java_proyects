package co.edu.javeriana.citasembajada.utils;

import java.io.Serializable;
import java.time.LocalDate;

public class Utils implements Serializable{

	public int obtenerEdad(LocalDate ahora, LocalDate fechaNacimiento)
	{
		int diaNacimiento = fechaNacimiento.getDayOfMonth();
		int mesNacimiento = fechaNacimiento.getMonthValue();
		int Edad = 0;
		
		if(ahora.getDayOfMonth() < mesNacimiento)
		{
			Edad = ahora.getYear() - fechaNacimiento.getYear()-1;
		}
		else
		{
			if( ahora.getDayOfMonth() == mesNacimiento)
			{
				if(diaNacimiento >= ahora.getDayOfMonth()) 
				{
					Edad = ahora.getYear() - fechaNacimiento.getYear();
				}
				else
				{
					Edad = ahora.getYear() - fechaNacimiento.getYear()-1;
				}
			}
			else 
			{
				Edad = ahora.getYear() - fechaNacimiento.getYear();
			}
			
		}
		return Edad;
	}
}
