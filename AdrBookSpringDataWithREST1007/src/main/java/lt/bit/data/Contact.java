package lt.bit.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "contact")
public class Contact {
    
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
        @ManyToOne
        @JoinColumn(name = "person_id")
        private Person person;
	private String type;
	private String contact;
	
	public Contact() {
		super();
	}

	public Contact(String type, String contact) {
		super();
		this.type = type;
		this.contact = contact;
	}

	public Integer getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getContact() {
		return contact;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
         public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
		Contact other = (Contact) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", person=" + person + ", type=" + type + ", contact=" + contact + '}';
    }

	
	
	
	
}
