package bg.softuni.container.models;


import bg.softuni.container.interfaces.Person;


public class Student implements Person {

    private int age = 16;

    @Override
    public void print() {
        System.out.println("I am SoftUni student, which is " + this.age + " years old");
    }
}
