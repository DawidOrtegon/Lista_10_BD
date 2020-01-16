import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
public class WinnersView
{

    @Id
    @Column(name = "award_id")
    private String award_id;
    @Column(name = "award_year")
    private int award_year;
    @Column(name = "nameAndLastName")
    private String nameAndLastName;
    @Column(name = "actorAge")
    private int actorAge;
    @Column(name = "movie")
    private String movie;
    @Column(name = "gender")
    private String gender;

    public WinnersView() {
    }


    public WinnersView(int award_year, String nameAndLastName, int actorAge, String movie, String gender, String award_id)
    {
        this.award_year = award_year;
        this.nameAndLastName = nameAndLastName;
        this.actorAge = actorAge;
        this.movie = movie;
        this.gender = gender;
        this.award_id = award_id;
    }

    public int getAward_year() {
        return award_year;
    }

    public void setAward_year(int award_year) {
        this.award_year = award_year;
    }

    public String getNameAndLastName() {
        return nameAndLastName;
    }

    public void setNameAndLastName(String nameAndLastName) {
        this.nameAndLastName = nameAndLastName;
    }

    public int getActorAge() {
        return actorAge;
    }

    public void setActorAge(int actorAge) {
        this.actorAge = actorAge;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAward_id() {
        return award_id;
    }

    public void setAward_id(String award_id) {
        this.award_id = award_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Prizes: ");
        sb.append("award_id = ").append(award_id).append(", ");
        sb.append("award_year = ").append(award_year).append(", ");
        sb.append("nameAndLastName = ").append(nameAndLastName).append(", ");
        sb.append("actorAge = ").append(actorAge).append(", ");
        sb.append("movie = ").append(movie).append(", ");
        sb.append("gender = ").append(gender).append(", ");

        return sb.toString();

    }
}
