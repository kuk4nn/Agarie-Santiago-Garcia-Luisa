package dao.Impl;

import dao.IDao;
import db.H2Connection;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    public static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);

    public static final String INSERT = "INSERT INTO ODONTOLOGOS VALUES(DEFAULT, ?,?,?)";
    public static final String SELECT_ALL = "SELECT * FROM ODONTOLOGOS";



    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoARetornar = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);



            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, odontologo.getNroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            preparedStatement.executeUpdate();
            connection.commit();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while(resultSet.next()){
                Integer id = resultSet.getInt(1);
                odontologoARetornar = new Odontologo(id, odontologo.getNroMatricula(), odontologo.getNombre(), odontologo.getApellido());

            }

            logger.info("Odontologo persistido " + odontologoARetornar);


        } catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex){
                logger.error(ex.getMessage());
                ex.printStackTrace();
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }

        return odontologoARetornar;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();
        Odontologo odontologo = null;

        try {
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);



            while(resultSet.next()){
                Integer id = resultSet.getInt(1);
                Integer nroMatricula = resultSet.getInt(2);
                String nombre = resultSet.getString(3);
                String apellido = resultSet.getString(4);
                odontologo = new Odontologo(id, nroMatricula, nombre, apellido);
                logger.info(odontologo);
                odontologos.add(odontologo);
            }

        } catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try{
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return odontologos;
    }

}
