package application;

import models.entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.next();

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Employee> employees = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                employees.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            System.out.print("Enter salary: ");
            Double salary = sc.nextDouble();

            List<String> emails = employees.stream()
                    .filter(emp -> emp.getSalary() > salary)
                    .map(emp -> emp.getEmail()).sorted()
                    .collect(Collectors.toList());

            System.out.println("Email of people whose salary is more than 2000.00: ");
            emails.forEach(System.out::println);

            double sum = employees.stream()
                    .filter(emp -> emp.getName().charAt(0) == 'M')
                    .map(emp -> emp.getSalary())
                    .reduce(0.0, (x,y) -> x + y);

            System.out.println("\nSum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));


        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
