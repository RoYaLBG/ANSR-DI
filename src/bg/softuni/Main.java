package bg.softuni;

import bg.softuni.container.Container;
import bg.softuni.container.interfaces.City;
import bg.softuni.container.interfaces.Home;
import bg.softuni.container.interfaces.Person;
import bg.softuni.container.models.House;
import bg.softuni.container.models.Kermen;
import bg.softuni.container.models.Student;

public class Main {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Container container = new Container();
        container.registerDependency(Home.class, House.class);
        container.registerDependency(City.class, Kermen.class);
        container.registerDependency(Person.class, Student.class);

        City city = container.resolve(City.class);

        city.print();
    }
}
