package student.management;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class mystudent {
    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Scanner sc = new Scanner(System.in);

            //-------------------------------------------Connecting to Database--------------------------------------------------------

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu_db", "root", "");
            statement = connection.createStatement();

            //-------------------------------------------Insert Data Into Database------------------------------------------------------

            System.out.println("Inserting record of a student in database....");
            int sid;
            String sname, dob;
            //statement.executeUpdate("insert into student values(125,'M. Johnson','1999-4-13')");
            preparedStatement = connection.prepareStatement("insert into stu_db.student values(?,?,?)");
            System.out.println("Enter student id, name, date of birth:");
            sid=sc.nextInt();
            sc.nextLine();
            sname=sc.nextLine();
            //sc.nextLine();
            dob=sc.nextLine();
            LocalDate sdob = LocalDate.parse(dob);

            preparedStatement.setInt(1,sid);
            preparedStatement.setString(2,sname);
            preparedStatement.setDate(3, Date.valueOf(sdob));
            preparedStatement.executeUpdate();

            //----------------------------------------View Data From Database-------------------------------------------

            resultSet = statement.executeQuery("select * from student");

            System.out.println("Showing all data from student table of database stu_db:");
            System.out.println("sid   Student Name   DateofBirth");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("sid") + "   " + resultSet.getString("sname")
                        + "      " + resultSet.getDate("sdob"));
            }

            //------------------------------------------Joining Two Tables----------------------------------------------

            System.out.println("Viewing all Student names who took course-332:");
            resultSet = statement.executeQuery(" select student.sname from student, course where course.sid=student.sid and course.cid=332");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("sname"));
            }

            System.out.println("Viewing all courses taken by student with id-123:");
            resultSet = statement.executeQuery(" select course.cname from student, course where course.sid=student.sid and student.sid=123");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("cname"));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}