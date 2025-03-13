package es.unican.is2.Common;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehiculoTest {
    
    Vehiculo vehiculo;
    
    @BeforeEach
    void setUp() {
        vehiculo = new Vehiculo(1, "1234ABC", LocalDate.of(2010, 6, 1), TipoMotor.GASOLINA) {
            @Override
            public double precioImpuesto() {
                return 100.0;
            }
        };
    }
    
    // Pruebas de caja negra (Partición Equivalente y AVL)
    @Test
    void testCalcularDescuento_VehiculoAntiguo() {
        vehiculo = new Vehiculo(2, "5678DEF", LocalDate.of(1990, 1, 1), TipoMotor.DIESEL) {
            @Override
            public double precioImpuesto() {
                return 100.0;
            }
        };
        assertEquals(1.0, vehiculo.calcularDescuento(), "El descuento para un vehículo de más de 20 años debe ser 1.0");
    }

    @Test
    void testCalcularDescuento_VehiculoElectrico() {
        vehiculo = new Vehiculo(3, "9101GHI", LocalDate.of(2020, 5, 20), TipoMotor.ELECTRICO) {
            @Override
            public double precioImpuesto() {
                return 100.0;
            }
        };
        assertEquals(0.8, vehiculo.calcularDescuento(), "El descuento para un vehículo eléctrico debe ser 0.8");
    }

    @Test
    void testCalcularDescuento_VehiculoHibridoNuevo() {
        vehiculo = new Vehiculo(4, "1111JKL", LocalDate.of(2023, 1, 15), TipoMotor.HIBRIDO) {
            @Override
            public double precioImpuesto() {
                return 100.0;
            }
        };
        assertEquals(0.7, vehiculo.calcularDescuento(), "El descuento para un híbrido de 5 años o menos debe ser 0.7");
    }

    @Test
    void testCalcularDescuento_VehiculoGasNuevo() {
        vehiculo = new Vehiculo(5, "2222MNO", LocalDate.of(2024, 1, 15), TipoMotor.GAS) {
            @Override
            public double precioImpuesto() {
                return 100.0;
            }
        };
        assertEquals(0.6, vehiculo.calcularDescuento(), "El descuento para un vehículo de gas de 2 años o menos debe ser 0.6");
    }

    @Test
    void testCalcularDescuento_SinDescuento() {
        assertEquals(0.0, vehiculo.calcularDescuento(), "Si no se cumplen condiciones, el descuento debe ser 0.0");
    }

    // Pruebas de caja blanca (Cobertura de decisión/condición)
    @Test
    void testCalcularDescuento_BordeAntiguedad20() {
        vehiculo = new Vehiculo(6, "3333PQR", LocalDate.of(2004, 1, 1), TipoMotor.DIESEL) {
            @Override
            public double precioImpuesto() {
                return 100.0;
            }
        };
        assertEquals(0.0, vehiculo.calcularDescuento(), "El descuento para un vehículo justo con 20 años debe ser 0.0");
    }
}
