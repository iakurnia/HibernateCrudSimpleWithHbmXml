package dao.impl;

import dao.PersonDao;
import model.Person;
import org.hibernate.Query;
import util.EntityBase;

import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl extends EntityBase implements PersonDao {

    @Override
    public String save(Person obj) {
        connect();
        String result = null;
        try {
            session.save(obj);
            session.flush();
            result = "success";
        } catch (Exception ex) {
            result = "failed";
            ex.printStackTrace();
        }
        System.out.println("Inset Data Person with name  " + obj.getName() + " saved");
        disconect();
        return result;
    }

    @Override
    public String update(Person obj) {
        connect();
        String result = null;
        try {
            session.update(obj);
            session.flush();
            result = "success";
        } catch (Exception ex) {
            result = "failed";
            ex.printStackTrace();
        }
        System.out.println("Update Data Person with name " + obj.getName() + " updated");
        disconect();
        return result;
    }

    @Override
    public String delete(Person obj) {
        connect();
        String result = null;
        try {
            session.delete(obj);
            session.flush();
            result = "success";
        } catch (Exception ex) {
            result = "failed";
            ex.printStackTrace();
        }
        System.out.println("Delete Data Person with id = " + obj.getId() + " deleted");
        disconect();
        return result;
    }

    @Override
    public Person findById(String id) {
        connect();
        Person person = null;
        try {
            Query tQuery = session.createQuery("FROM Person WHERE id=:id");
            tQuery.setParameter("id", id);
            if (tQuery.list().size() > 0) {
                person = (Person) tQuery.list().get(0);
                System.out.println("Find Data Person with id= " + person.getId() + " found");
            }else{
                System.out.println("Data Not Found with id= "+id);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        disconect();
        return person;
    }

    @Override
    public List<Person> findByName(String name) {
        connect();
        List<Person> list = queryFieldAndValue ("name", name);
        disconect();
        if (list.isEmpty()) {
            list = new ArrayList<Person>();
            System.out.println("Data Not Found with name= "+name);
        }else{
            System.out.println("Data Person with name : " + name + " is " + list.size() + " found");
        }
        return list;
    }

    @Override
    public List<Person> findAll() {
        connect();
        List<Person> list = session.createQuery("FROM Person").list();
        if (list.isEmpty()) {
            list = new ArrayList<Person>();
            System.out.println("Data Not Found");
        }else{
            System.out.println("Data Person is " + list.size() + " found");
        }
        disconect();
        return list;
    }

    @Override
    public List<Person> finByFieldValue(String field, String value) {
        connect();
        List<Person>  list = this.queryFieldAndValue(field,value);
        disconect();
        return list;
    }
    private List<Person> queryFieldAndValue(String field, String value){
        Query q = session.createQuery("from Person where " + field + " like :value");
        q.setParameter("value", "%" + value + "%");
        return q.list();
    }
}
