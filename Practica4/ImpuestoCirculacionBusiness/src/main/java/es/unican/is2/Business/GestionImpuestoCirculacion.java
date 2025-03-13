package es.unican.is2.Business;

import es.unican.is2.Common.*;

public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, IInfoImpuestoCirculacion {

    private IContribuyentesDAO daoContribuyentes;
    private IVehiculosDAO daoVehiculos;

    public GestionImpuestoCirculacion(IContribuyentesDAO daoContribuyentes, IVehiculosDAO daoVehiculos) {
        this.daoContribuyentes = daoContribuyentes;
        this.daoVehiculos = daoVehiculos;
    }

    @Override
    public Contribuyente contribuyente(String dni) throws DataAccessException {
        return daoContribuyentes.contribuyente(dni);
    }

    @Override
    public Contribuyente altaContribuyente(Contribuyente c) throws DataAccessException {
        if (contribuyente(c.getDni()) != null) {
            return null;
        }
        return daoContribuyentes.creaContribuyente(c);
    }

    @Override
    public Contribuyente bajaContribuyente(String dni) throws OperacionNoValidaException, DataAccessException {
        Contribuyente contribuyente = contribuyente(dni);
        if (contribuyente == null) {
            return null;
        }
        if (contribuyente.getVehiculos() != null && !contribuyente.getVehiculos().isEmpty()) {
            throw new OperacionNoValidaException("El contribuyente tiene vehículos registrados, no puede eliminarse.");
        }
        return daoContribuyentes.eliminaContribuyente(dni);
    }

    @Override
    public Vehiculo vehiculo(String matricula) throws DataAccessException {
        return daoVehiculos.vehiculoPorMatricula(matricula);
    }

    @Override
    public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValidaException, DataAccessException {
        if (vehiculo(v.getMatricula()) != null) {
            throw new OperacionNoValidaException("El vehículo con esa matrícula ya está registrado.");
        }

        Contribuyente propietario = contribuyente(dni);
        if (propietario == null) {
            return null;
        }

        daoVehiculos.creaVehiculo(v);
        propietario.getVehiculos().add(v);

        return v;
    }

    @Override
    public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValidaException, DataAccessException {
        Contribuyente propietario = contribuyente(dni);
        Vehiculo vehiculo = vehiculo(matricula);

        if (propietario == null || vehiculo == null || propietario.buscaVehiculo(matricula) == null) {
            throw new OperacionNoValidaException("El vehículo no está asociado al contribuyente.");
        }

        daoVehiculos.eliminaVehiculo(matricula);
        propietario.getVehiculos().remove(vehiculo);

        return vehiculo;
    }

    @Override
    public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo)
            throws OperacionNoValidaException, DataAccessException {

        Contribuyente propietarioActual = contribuyente(dniActual);
        Contribuyente nuevoPropietario = contribuyente(dniNuevo);
        Vehiculo vehiculo = vehiculo(matricula);

        if (propietarioActual == null || nuevoPropietario == null || vehiculo == null) {
            return false;
        }

        if (propietarioActual.buscaVehiculo(matricula) == null) {
            throw new OperacionNoValidaException("El vehículo no pertenece al contribuyente indicado.");
        }

        propietarioActual.getVehiculos().remove(vehiculo);
        nuevoPropietario.getVehiculos().add(vehiculo);
        return true;
    }
}
