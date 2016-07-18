package bg.softuni.container.models;


import bg.softuni.container.annotations.Inject;
import bg.softuni.container.interfaces.City;
import bg.softuni.container.interfaces.Home;
import bg.softuni.container.interfaces.Person;


public class Kermen implements City {

    @Inject
    private Home home;

    @Inject
    private Person person;

    @Override
    public void print() {
        System.out.println("It's kermen here");
        System.out.println("I have a home an a person");
        System.out.println("Let's print 'Person'");
        this.person.print();
        System.out.println("Let's print 'home'");
        this.home.print();
    }

    @Override
    public void printSelfOnly() {
        System.out.println("KERMEN: V SLUCHAI CHE ME IZVIKAT CIKLICHNO");
    }
}
