package dao.Impl;

import dao.IDao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2Memoria implements IDao<Odontologo> {

    public static final Logger logger = Logger.getLogger(OdontologoDaoH2Memoria.class);
    public List<Odontologo> odontologos = new ArrayList<>();

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        odontologo.setId(odontologos.size() + 1);
        odontologos.add(odontologo);
        logger.info("Odontologo guardado " + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        for (Odontologo odontologo: odontologos){
            logger.info(odontologo);
        }
        return odontologos;
    }
}
