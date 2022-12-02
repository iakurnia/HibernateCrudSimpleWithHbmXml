package controller;

import dao.impl.PersonDaoImpl;
import model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class RunApplicationController {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PersonDaoImpl personDaoImpl = new PersonDaoImpl();;
    static Person person = new Person();

    public static void menu(){
        System.out.println("1. Create");
        System.out.println("2. Update");
        System.out.println("3. Delete");
        System.out.println("4. Find with Name");
        System.out.println("5. Find All");
        System.out.println("6. Exit");
    }
    public static void save() throws IOException {
        System.out.print("Input Your Id : ");
        person.setId(br.readLine());
        System.out.print("Input Your Name : ");
        person.setName(br.readLine());
        System.out.print("Input Your Addrees/Country : ");
        person.setAddress(br.readLine());
        System.out.print("Input Your Gender : ");
        person.setGender(br.readLine());
        if(findById(person.getId()) !=null){
            System.out.println("Data Already Exist !!");
        }else{
            personDaoImpl.save(person);
        }
    }
    public static void update() throws IOException {
        System.out.print("Input Your Id : ");
        person.setId(br.readLine());
        if(findById(person.getId()) ==null){
            System.out.println("Data Not Found !!");
        }else{
            System.out.print("Update Your Name : ");
            person.setName(br.readLine());
            System.out.print("Update Your Addrees/Country : ");
            person.setAddress(br.readLine());
            System.out.print("Update Your Gender : ");
            person.setGender(br.readLine());
            personDaoImpl.update(person);
        }
    }
    public static void delete() throws IOException {
        System.out.print("Input Your Id : ");
        person.setId(br.readLine());
        if(findById(person.getId()) ==null){
            System.out.println("Data Not Found !!");
        }else{
            personDaoImpl.delete(person);
        }
    }
    public static Person findById(String id){
        return personDaoImpl.findById(id);
    }
    public static void findByName() throws IOException {
        System.out.print("Input Your Name : ");
        person.setName(br.readLine());
        List<Person> listName = personDaoImpl.findByName(person.getName());
        if(listName.isEmpty()){
            System.out.println("Data Not Found");
        }
        for (Person p:listName) {
            System.out.println(p.toString());
        }
    }
    public static void findAll(){
        List<Person> listAll = personDaoImpl.findAll();
        if(listAll.isEmpty()){
            System.out.println("Data Not Found");
        }
        for (Person p:listAll) {
            System.out.println(p.toString());
        }
    }
    public static void main(String[] args) throws IOException {
        System.out.println("Run Aplication Hibernate Simple");
        menu();
        boolean repeat = true;
        do{
            String choose;
            System.out.print("Choose : ");
            choose=br.readLine();
            if(choose.equals("1")){
                save();
            }else if(choose.equals("2")){
                update();
            }else if(choose.equals("3")){
                delete();
            }else if(choose.equals("4")){
                findByName();
            }else if(choose.equals("5")){
                findAll();
            }else if(choose.equals("6")){
                System.out.println("Thank You");
                repeat=false;
            }else{
                System.out.println("Your Input invalid");
            }
        }while (repeat);
    }
}
