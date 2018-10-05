package lk.ijse.comp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class CrudDAOImpl<T,ID> implements CrudDAO<T,ID> {

    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> entity;

    public CrudDAOImpl() {
        entity = (Class<T>)(((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void Save(T entity) throws Exception {

        getSession().persist(entity);
    }

    @Override
    public void Update(T entity) throws Exception {
        getSession().persist(entity);
    }

    @Override
    public void Delete(ID id) throws Exception {
        T t = getSession().find(entity, id);
        getSession().remove(t);

    }

    @Override
    public List<T> getAll() throws Exception {
        return getSession().createQuery("FROM "+ entity.getName()).list();
    }

    @Override
    public T findById(ID id) throws Exception {
       return getSession().find(entity,id);
    }


}
