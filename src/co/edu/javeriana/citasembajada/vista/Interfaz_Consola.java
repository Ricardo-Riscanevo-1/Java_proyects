package co.edu.javeriana.citasembajada.vista;
/**
 * @author Ricardo Riscanevo-Juan Sebastian Solano
 **/
import java.util.Scanner;

public class Interfaz_Consola {
	//--------------------
	//	Constantes
	//--------------------

	//--------------------
	//	Atributos
	//-------------------

	private Scanner sc;

	public Interfaz_Consola() 
	{
		sc = new Scanner(System.in);
	}

	public int mostrarMenu() throws NumberFormatException
	{
		imprimir("SISTEMA SOLICITUD DE CITAS PARA EMBAJADA  ");
		imprimir("Seleccione una opción:");
		imprimir( " opción 0:  Asociar país a la Embajada");
		imprimir( " opción 1:  Ingresar solicitantes");
		imprimir( " opción 2:  Ingresar tipos de visa");
		imprimir( " opción 3:  Hacer solicitud de cita para visa de Turismo");
		imprimir( " opción 4:  Hacer solicitud de cita para visa diferente a Turismo");
		imprimir( " opción 5:  Calcular valor de visa");
		imprimir( " opción 6:  Reporte de citas para una determinada fecha");
		imprimir( " opción 7:  Consultar la lista de requisitos de un tipo de Visa");
		imprimir( " opción 8:  Consultar la lista de beneficiarios  ");
		imprimir( " opción 9:  Salir ");

		int opcionSeleccionada = leerInt("Seleccione una opcion");

		return opcionSeleccionada;
	}

	/**
	 * 
	 * @param mensaje
	 */
	public int leerInt(String enunciado)
	{
		imprimir(enunciado+":");
		System.out.print(" > ");
		int numero = sc.nextInt();
		return numero;
	}

	/**
	 * 
	 * @param mensaje
	 */
	public void imprimir(String mensaje)
	{
		System.out.println(mensaje);
	}

	/**
	 * 
	 * @param mensaje
	 */
	public void mostrarError(String mensaje) 
	{
		imprimir("===========================");
		imprimir("   ERROR EN: " +mensaje);
		imprimir("===========================");
	}
	public void mostrarRecuadro(String mensaje) 
	{
		imprimir("===========================");
		imprimir("   " +mensaje);
		imprimir("===========================");
	}

	/**
	 * 
	 * @param mensaje
	 */
	public String leerString(String enunciado)
	{
		imprimir(enunciado+":");
		System.out.print(" > ");
		String texto = sc.nextLine();
		texto = sc.nextLine();
		return texto;
	}

	/**
	 * 
	 * @param mensaje
	 */
	public float leerFloat(String enunciado)
	{
		imprimir(enunciado+":");
		System.out.print(" > ");
		float numero = sc.nextFloat();
		return numero;
	}

	/**
	 * 
	 * @param mensaje
	 */
	public double leerDouble(String enunciado)
	{
		imprimir(enunciado+":");
		System.out.print(" > ");
		double numero = sc.nextDouble();
		return numero;
	}
	public void quitarEnter()
	{
		sc.nextLine();
	}

}
