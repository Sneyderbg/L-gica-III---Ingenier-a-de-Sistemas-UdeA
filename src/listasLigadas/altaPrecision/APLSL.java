package listasLigadas.altaPrecision;

import listasLigadas.listas.LSL;
import listasLigadas.nodos.NodoSimple;

public class APLSL extends LSL 
{
	NodoSimple Acarreo = new NodoSimple(0);
	
	public APLSL() { Insertar(0, primerNodo); }
	public APLSL(String s)
	{
		s = s.replaceAll(" ", "");
		int x, i; 
		for (i=s.length(); i>3; i=i-4)
		{
			x = Integer.parseInt(s.substring(i-4, i));
			Insertar(x, null);
		}
		x = Integer.parseInt(s.substring(0, i));
		Insertar(x, null);
	}
	public APLSL Sumar(APLSL b)
	{
		APLSL c = new APLSL();
		NodoSimple x, y;
		Invertir();
		b.Invertir();
		x = getPrimerNodo();
		y = getPrimerNodo();
		while (!FinDeRecorrido(x) && !FinDeRecorrido(y))
		{
			c.Operar((int) x.getDato(),(int) y.getDato(), "+");
			x = x.getLiga();
			y = y.getLiga();
		}
		while (!FinDeRecorrido(x))
		{
			c.Operar((int) x.getDato(), 0, "+");
			x = x.getLiga();
		}
		while (!FinDeRecorrido(y))
		{
			c.Operar((int) y.getDato(), 0, "+");
			y = y.getLiga();
		}
		if (Acarreo.getDato().hashCode()!=0) { c.Operar(0, 0, "+"); }
		Invertir();
		b.Invertir();
		return c;
	}
	public APLSL Restar(APLSL b)
	{
		APLSL c = new APLSL();
		// NodoSimple x, y;
		return c;
	}
	protected void Operar(int x, int y, String Op)
	{
		if (Op=="+")
		{
			int d = x + y + (int) Acarreo.getDato();
			Acarreo.setDato(d/10000);
			Insertar(d%10000, null);
		}
	}
}
