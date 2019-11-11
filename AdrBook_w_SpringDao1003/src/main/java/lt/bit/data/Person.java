package lt.bit.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "person")
//@NamedQueries({
//@NamedQuery(name = "Person.byFirstName", query = "select p from Person p order by p.firstName"),
//@NamedQuery(name = "Person.byLastName", query = "select p from Person p order by p.lastName")
//})
public class Person {
	
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
        @Column(name = "first_name")
	private String firstName;
        @Column(name = "last_name")
	private String lastName;
        @Column(name = "birth_date")
        @Temporal(TemporalType.DATE)
	private Date birthDate;
	private BigDecimal salary;
        @OneToMany(mappedBy = "person")
//        @JoinColumn(name = "person_id")
	private List<Address> addresses;
        @OneToMany(mappedBy = "person")
//        @JoinColumn(name = "person_id")
	private List<Contact> contacts;
	
	public Person() {
		super();
		this.addresses = new ArrayList<>();
		this.contacts = new ArrayList<>();
	}

	public Person(String firstName, String lastName, Date birthDate, BigDecimal salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.salary = salary;
		this.addresses = new ArrayList<>();
		this.contacts = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + ", salary=" + salary + '}';
    }

    
	
	
}
