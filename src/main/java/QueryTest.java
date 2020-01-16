import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import javax.transaction.TransactionScoped;
import java.util.*;

public class QueryTest {

    private static SessionFactory factory;

    public static void main(String[] args) {


        try {
            factory = new Configuration().configure().buildSessionFactory().openSession().getSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();


/*
        Para ver el nombre de en los queries correcto, se tiene que usar el nombre exacto que se puso en la tabla que se
                creo. Tambien para ver la tabla completa se pone from sin el SELECT.
*/

        System.out.println("To check the winners of the database: ");
        showQueryResults("from WinnersView");

        /*Siempre se maneja el mismo esquema de primero la sesion de factory, despues la transaccion, despues
        con la funcion de showqueryresult se llaman a los datos. */

        System.out.println("To check the standard ddeviation in the age of the actors: ");
        showQueryResults("SELECT stddev(actorAge) FROM WinnersView");
        System.out.println();

        System.out.println("To check the number of Oscars that belong to the Fonda Family: ");
        showQueryResults("SELECT COUNT(award_year) from WinnersView where nameAndLastName like '%Fonda'");
        System.out.println();

        System.out.println("See the age of the actors:");
        showQueryResults("SELECT actorAge FROM WinnersView");
        System.out.println();

        System.out.println("Select the average of the age of the actors:");
        showQueryResults("SELECT avg(actorAge) FROM WinnersView");
        System.out.println();

        System.out.println("The minimal age for the winners who are man is: ");
        showQueryResults("select min(actorAge) from WinnersView where gender = 'M'");
        System.out.println();

        System.out.println("The minimal age for the winners who are man is: ");
        showQueryResults("select min(actorAge) from WinnersView where gender = 'F'");
        System.out.println();

        System.out.println("Show the percentage of women winners: ");
        showQueryResults("select (count(gender)/177)*100 from WinnersView where gender = 'F'");
        System.out.println();

        /*Cuando se hace una lista con los contadores, se crea sin las cosas que se usan sin las referencias a las tablas y de forma mas directa. */

        System.out.println("The number of prices according to the last name: ");
        showQueryResults("select new list(count(nameAndLastName), nameAndLastName) from WinnersView group by nameAndLastName having count(nameAndLastName) > 1 order by count(nameAndLastName) desc");
        System.out.println();

        System.out.println("The number of prices according to the year is: ");
        showQueryResults("select new list(count(award_year), award_year) from WinnersView group by award_year having count(award_year) > 1 order by count(award_year) asc");
        System.out.println();


        System.out.println("Check the table of the favorites and year of the prices");
        showQueryResults("select new list(award_year, movie, nameAndLastName) from WinnersView where (nameAndLastName like 'Al Pacino'  or nameAndLastName like 'Tom Hanks' or nameAndLastName like 'Colin Firth')");
        System.out.println();

        System.out.println("Count the number of men and women inside the Winners");
        showQueryResults("select new list(gender, count(gender)) from WinnersView group by gender having count(gender) > 1");
        System.out.println();



        session.getTransaction().commit();
        session.close();
    }


    public static void showQueryResults(String queryString) {

        Session session = factory.openSession();
        Query query = session.createQuery(queryString);
        List list = query.list();

        for (int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));

    }
}
