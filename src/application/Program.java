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
        List<Employee> employees = new ArrayList<>();

        System.out.print("Enter full file path: ");
        String path = sc.next();

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                employees.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));

                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Enter salary: ");
        Double salary = sc.nextDouble();
        System.out.println("Email of people whose salary is more than 2000.00: ");

        Comparator<String> comp = (e1, e2) -> e1.toUpperCase().compareTo(e2.toUpperCase());

        List<String> highSalary = employees.stream()
                .filter(emp -> emp.getSalary() > salary)
                .map(emp -> emp.getEmail()).sorted(comp)
                .collect(Collectors.toList());

        highSalary.forEach(emp -> {
            System.out.println(emp);
        });

        System.out.println("Sum of salary of people whose name starts with 'M': ");
        List<Double> sumSalary = employees.stream()
                .filter(emp -> emp.getName().charAt(0) == 'M')
                .map(emp -> emp.getSalary())
                .collect(Collectors.toList());

        Double sum = 0.0;
        for (Double e : sumSalary) {
            sum += e;
        }

        System.out.print(sum);

        sc.close();
    }
}
