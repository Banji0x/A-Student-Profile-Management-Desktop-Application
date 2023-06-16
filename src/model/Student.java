package model;

public record Student(String firstName, String lastName, int age, String matricNumber, String department,
                      String faculty) {
    public Student(String firstName, String lastName, String age, String matricNumber, String department, String faculty) {
      this(firstName,lastName,Integer.parseInt(age),matricNumber,department,faculty);
    }
}
