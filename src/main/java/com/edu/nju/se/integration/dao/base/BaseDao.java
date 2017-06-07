package com.edu.nju.se.integration.dao.base;

import com.edu.nju.se.integration.model.base.BaseTable;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Created by darxan on 2017/2/13.
 */
public class BaseDao<T> {

    @Autowired
    protected SessionFactory sessionFactory;


    protected Class<T> entityClass;

    public BaseDao() {
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Criteria getCriteria() {
        return getSession().createCriteria(entityClass);
    }

    protected Class getEntityClass() {
        return entityClass;
    }

    /**
     * <保存实体>
     * <完整保存实体>
     *
     * @param t 实体参数
     */
    public void save(T t) {
        this.getSession().save(t);
    }

    /**
     * <保存或者更新实体>
     *
     * @param t 实体
     */
    public void saveOrUpdate(T t) {
        this.getSession().saveOrUpdate(t);
    }

    /**
     * <load>
     * <加载实体的load方法>
     *
     * @param id 实体的id
     * @return 查询出来的实体
     */
    public T load(Serializable id) {
        return (T) this.getSession().load(getEntityClass(), id);
    }

    /**
     * <get>
     * <查找的get方法>
     *
     * @param id 实体的id
     * @return 查询出来的实体
     */
    public T get(Serializable id) {
        return  (T) this.getSession().get(getEntityClass(), id);
    }


    /**
     *
     * @param name
     * @return
     */
    public T getByName(String name) {
        return getByPair("name", name);
    }
    /**
     * <contains>
     *
     * @param t 实体
     * @return 是否包含
     */
    public boolean contains(T t) {
        return this.getSession().contains(t);
    }

    /**
     * <delete>
     * <删除表中的t数据>
     *
     * @param t 实体
     */
    public void delete(T t) {
        this.getSession().delete(t);
    }


    /**
     * <根据ID删除数据>
     *
     * @param Id 实体id
     * @return 是否删除成功
     */
    public boolean deleteById(Serializable Id) {
        T t = get(Id);
        if (t == null) {
            return false;
        }
        delete(t);
        return true;
    }

    /**
     * <删除所有>
     *
     * @param entities 实体的Collection集合
     */
    public void deleteAll(Collection<T> entities) {
        for (Object entity : entities) {
            this.getSession().delete(entity);
        }
    }


    /**
     * <执行Hql语句>
     *
     * @param hqlString hql
     * @param values    不定参数数组
     */
    public void queryHql(String hqlString, Object... values) {
        Query query = this.getSession().createQuery(hqlString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        query.executeUpdate();
    }

    /**
     * <执行Sql语句>
     *
     * @param sqlString sql
     * @param values    不定参数数组
     */
    public void querySql(String sqlString, Object... values) {
        Query query = this.getSession().createSQLQuery(sqlString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        query.executeUpdate();
    }

    /**
     * <根据HQL语句查找唯一实体>
     *
     * @param hqlString HQL语句
     * @param values    不定参数的Object数组
     * @return 查询实体
     */
    public T getByHQL(String hqlString, Object... values) {
        Query query = this.getSession().createQuery(hqlString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return (T) query.uniqueResult();
    }

    public T getByPair(String key, Object value) {
        Criteria criteria = getSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq(key, value));
        criteria.setMaxResults(1);
        return (T)criteria.uniqueResult();
    }

    public T getByMap(Map<String, String> map) {
        Criteria criteria = getSession().createCriteria(getEntityClass());
        for (String key:map.keySet()) {
            criteria.add(Restrictions.eq(key, map.get(key)));
        }
        criteria.setMaxResults(1);
        return (T)criteria.uniqueResult();
    }

    public List<T> getListByPair(String key, Object value) {
        Criteria criteria = getSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq(key, value));;
        return criteria.list();
    }

    public List<T> getListByMap(Map<String, String> map) {
        Criteria criteria = getSession().createCriteria(getEntityClass());
        for (String key:map.keySet()) {
            criteria.add(Restrictions.eq(key, map.get(key)));
        }
        criteria.setMaxResults(1);
        return criteria.list();
    }

    /**
     * <根据SQL语句查找唯一实体>
     *
     * @param sqlString SQL语句
     * @param values    不定参数的Object数组
     * @return 查询实体
     */
    public T getBySQL(String sqlString, Object... values) {
        Query query = this.getSession().createSQLQuery(sqlString).addEntity(getEntityClass());
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return (T) query.uniqueResult();
    }

    /**
     * <根据HQL语句，得到对应的list>
     *
     * @param hqlString HQL语句
     * @param values    不定参数的Object数组
     * @return 查询多个实体的List集合
     */
    public List<T> getListByHQL(String hqlString, Object... values) {
        Query query = this.getSession().createQuery(hqlString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }

        return query.list();
    }

    /**
     * <根据SQL语句，得到对应的list>
     *
     * @param sqlString HQL语句
     * @param values    不定参数的Object数组
     * @return 查询多个实体的List集合
     */
    public List<T> getListBySQL(String sqlString, Object... values) {
        Query query = this.getSession().createSQLQuery(sqlString).addEntity(getEntityClass());
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
    }


    /**
     * <refresh>
     *
     * @param t 实体
     */
    public void refresh(T t) {
        this.getSession().refresh(t);
    }

    /**
     * <update>
     *
     * @param t 实体
     */
    public void update(T t) {
        this.getSession().update(t);
    }

    /**
     * <根据HQL得到记录数>
     *
     * @param hql    HQL语句
     * @param values 不定参数的Object数组
     * @return 记录总数
     */
    public Long countByHql(String hql, Object... values) {
        Query query = this.getSession().createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return (Long) query.uniqueResult();
    }


    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @return session
     */
    public Session getSession() {
        //需要开启事物，才能得到CurrentSession
        return sessionFactory.getCurrentSession();
    }

}
