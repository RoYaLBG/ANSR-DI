package bg.softuni.container.models;

import bg.softuni.container.interfaces.Person;


public class GrandFather implements Person {

    @Override
    public void print() {
        System.out.println("Another person implementation. You can change it in `registerDependency` method. I am a grandfather!");
    }
}
