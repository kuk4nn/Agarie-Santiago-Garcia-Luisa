package service;

import dao.IDao;
import model.Odontologo;

import java.util.List;

public class OdontologoService {

    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoIDao.guardarOdontologo(odontologo);
    }

    public List<Odontologo> busarTodos(){
        return odontologoIDao.buscarTodos();
    }



}
