package bg.softuni.container.models;


import bg.softuni.container.annotations.Inject;
import bg.softuni.container.interfaces.City;
import bg.softuni.container.interfaces.Home;
import bg.softuni.container.interfaces.Person;


public class House implements Home {

    @Inject
    private Person person;

    @Inject
    private City city;

    private int age = 24;

    @Override
    public void print() {
        System.out.println("I'm a house (home). I have a private field that is not @inject : " + this.age);
        System.out.println("I have a cyclic dependency of 'City', but it shouldn't be a problem:");
        this.city.printSelfOnly();
        System.out.println("I also have a 'Person':");
        this.person.print();
    }
}
