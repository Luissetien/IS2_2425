package es.unican.is2.Common;

import java.time.LocalDate;

/**
 * Clase que representa un vehiculo de tipo motocicleta
 */
public class Motocicleta extends Vehiculo {

	private int cilindrada;

	public Motocicleta(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, int cilindrada) {
		super(id, matricula, fechaMatriculacion, motor);
		
		this.cilindrada = cilindrada;
	}

	/**
	 * Retorna la cilindrada en CC de la motocicleta.
	 */
	public int getCilindrada() {
		return cilindrada;
	}

	@Override
	public double precioImpuesto() {
		
		double precioFinal = 0;
		
	    double descuentoAplicado = calcularDescuento();
	    
	    if (cilindrada < 100) {
	    	
	        precioFinal = 10;
	    }
	    
	    else if (cilindrada >= 100 && cilindrada < 200) {
	    	
	        precioFinal = 18;
	    }
	    
	    else if (cilindrada >= 200 && cilindrada < 400) {
	    	
	        precioFinal = 35;
	    }
	    
	    else if (cilindrada >= 400 && cilindrada < 900) {
	    	
	        precioFinal = 75;
	    }
	    
	    else if (cilindrada >= 900) {
	    	
	        precioFinal = 150;
	    }

	    return descuentoAplicado * precioFinal;
	}

}
