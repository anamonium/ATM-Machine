package entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "validatePIN.byCard", query = "Select c from CardEntity c where c.cardNumber = ?1 and c.pin = ?2")
@NamedStoredProcedureQuery(name = "withdrawMoney", procedureName = "withdrawMoney",
                            parameters = {
                                    @StoredProcedureParameter(mode = ParameterMode.IN, type = Double.class, name = "amount"),
                                    @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "cardNo"),
                                    @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "result")
                            })
@NamedQuery(name = "getStatus", query = "select c.status from CardEntity c where c.cardNumber = ?1")
@Table(name = "Card", schema = "ATMdatabase")
public class CardEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_card")
    private int idCard;
    @Basic
    @Column(name = "cardNumber")
    private String cardNumber;
    @Basic
    @Column(name = "PIN")
    private String pin;
//    @Basic
//    @Column(name = "ID_person_card")
//    private Integer idPersonCard;
    @ManyToOne
    @JoinColumn(name = "ID_person_card", referencedColumnName = "ID_person")
    private PersonEntity personByIdPersonCard;
//    @Basic
//    @Column(name = "ID_account_card")
//    private Integer idAccoundCard;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_account_card", referencedColumnName = "ID_account")
    private AccountEntity accountByIdAccoundCard;
    @Basic
    @Column(name = "status")
    private Integer status;

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

//    public Integer getIdPersonCard() {
//        return idPersonCard;
//    }

//    public void setIdPersonCard(Integer idPersonCard) {
//        this.idPersonCard = idPersonCard;
//    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardEntity that = (CardEntity) o;

        if (idCard != that.idCard) return false;
        if (cardNumber != that.cardNumber) return false;
        if (pin != that.pin) return false;
        //if (idPersonCard != null ? !idPersonCard.equals(that.idPersonCard) : that.idPersonCard != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCard;
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (pin != null ? pin.hashCode() : 0);
        //result = 31 * result + (idPersonCard != null ? idPersonCard.hashCode() : 0);
        return result;
    }

    public PersonEntity getPersonByIdPersonCard() {
        return personByIdPersonCard;
    }

//    public Integer getIdAccoundCard() {
//        return idAccoundCard;
//    }
//
//    public void setIdAccoundCard(Integer idAccoundCard) {
//        this.idAccoundCard = idAccoundCard;
//    }

    public void setPersonByIdPersonCard(PersonEntity personByIdPersonCard) {
        this.personByIdPersonCard = personByIdPersonCard;
    }

    public AccountEntity getAccountByIdAccoundCard() {
        return accountByIdAccoundCard;
    }

    public void setAccountByIdAccoundCard(AccountEntity accountByIdAccoundCard) {
        this.accountByIdAccoundCard = accountByIdAccoundCard;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
