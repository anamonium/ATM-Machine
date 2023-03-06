package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Person", schema = "ATMdatabase")
public class PersonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_person")
    private int idPerson;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "login")
    private String login;
    @Basic
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "personByIdPersonAccount")
    private Collection<AccountEntity> accountsByIdPerson;
    @OneToMany(mappedBy = "personByIdPersonCard")
    private Collection<CardEntity> cardsByIdPerson;

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (idPerson != that.idPerson) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPerson;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public Collection<AccountEntity> getAccountsByIdPerson() {
        return accountsByIdPerson;
    }

    public void setAccountsByIdPerson(Collection<AccountEntity> accountsByIdPerson) {
        this.accountsByIdPerson = accountsByIdPerson;
    }

    public Collection<CardEntity> getCardsByIdPerson() {
        return cardsByIdPerson;
    }

    public void setCardsByIdPerson(Collection<CardEntity> cardsByIdPerson) {
        this.cardsByIdPerson = cardsByIdPerson;
    }
}
