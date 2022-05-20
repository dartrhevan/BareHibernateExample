package ru.javastudy.hibernate.main;

public class AppMain {

    public static void main(String[] args) {
        GeneratingService generatingService = new GeneratingService();
        generatingService.generateAndPersistEntities();
        generatingService.printAllEntities();
    }

}
