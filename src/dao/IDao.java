package dao;


import java.util.List;

public interface IDao<T> {
    T guardarOdontologo(T t);
    List<T> buscarTodos();
}
