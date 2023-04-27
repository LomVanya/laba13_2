package org.example;

public class Tester {
    private String name;
    private String surname;
    private int experienceInYears;
    private String englishLevel;
    private double salary;

    public Tester(String name, String surname, int experienceInYears, String englishLevel, double salary) {
        this.name = name;
        this.surname = surname;
        this.experienceInYears = experienceInYears;
        this.englishLevel = englishLevel;
        this.salary = salary;
    }

    public Tester(String name, String surname, int experienceInYears, String englishLevel) {
        this(name, surname, experienceInYears, englishLevel, 0.0);
    }

    public Tester(String name, String surname, int experienceInYears) {
        this(name, surname, experienceInYears, "", 0.0);
    }


    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Experience in years: " + experienceInYears);
        System.out.println("English level: " + englishLevel);
        System.out.println("Salary: " + salary);
    }

    public void printInfo(boolean showSalary) {
        if (showSalary = true) {
            System.out.println("Name: " + name);
            System.out.println("Surname: " + surname);
            System.out.println("Salary: " + salary);
        }
        else {
            printInfo();
        }
    }

    public void printInfo(boolean showSalary, boolean showExperienceInMonths) {
        if (showSalary = true) {
            System.out.println("Name: " + name);
            System.out.println("Surname: " + surname);
            System.out.println("Salary: " + salary);
        }
        else {
            System.out.println("Name: " + name);
            System.out.println("Surname: " + surname);
        }
        if (showExperienceInMonths = true) {
            int months = experienceInYears * 12;
            System.out.println("Experience in month: " + months);
        }
        else {
            System.out.println("Experience in years: " + experienceInYears);
        }
    }

    public static void doSomethingStatic() {
        System.out.println("Static METOD");
    }
}
