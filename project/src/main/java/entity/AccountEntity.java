package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQuery(name = "getAccount", query = "select a from AccountEntity a where a.idAccount = (select c.accountByIdAccoundCard.idAccount from CardEntity c where c.cardNumber = ?1)")
@Table(name = "Account", schema = "ATMdatabase")
public class AccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_account")
    private int idAccount;
    @Basic
    @Column(name = "balance")
    private Double balance;
    @Basic
    @Column(name = "accountNumber")
    private String accountNumber;
//    @Basic
//    @Column(name = "ID_person_account")
//    private Integer idPersonAccount;
    @ManyToOne
    @JoinColumn(name = "ID_person_account", referencedColumnName = "ID_person")
    private PersonEntity personByIdPersonAccount;
    @OneToMany(mappedBy = "accountByIdAccoundCard")
    private Collection<CardEntity> cardsByIdAccount;

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

//    public Integer getIdPersonAccount() {
//        return idPersonAccount;
//    }
//
//    public void setIdPersonAccount(Integer idPersonAccount) {
//        this.idPersonAccount = idPersonAccount;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountEntity that = (AccountEntity) o;

        if (idAccount != that.idAccount) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        if (accountNumber != null ? !accountNumber.equals(that.accountNumber) : that.accountNumber != null)
            return false;
//        if (idPersonAccount != null ? !idPersonAccount.equals(that.idPersonAccount) : that.idPersonAccount != null)
//            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAccount;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        //result = 31 * result + (idPersonAccount != null ? idPersonAccount.hashCode() : 0);
        return result;
    }

    public PersonEntity getPersonByIdPersonAccount() {
        return personByIdPersonAccount;
    }

    public void setPersonByIdPersonAccount(PersonEntity personByIdPersonAccount) {
        this.personByIdPersonAccount = personByIdPersonAccount;
    }

    public Collection<CardEntity> getCardsByIdAccount() {
        return cardsByIdAccount;
    }

    public void setCardsByIdAccount(Collection<CardEntity> cardsByIdAccount) {
        this.cardsByIdAccount = cardsByIdAccount;
    }
}
