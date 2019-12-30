package sec.project.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Signup extends AbstractPersistable<Long> {

    private String name;
    private String address;
    private String info;
    @ManyToOne
    private Event event;
    
    public Signup() {
        super();
    }

    public Signup(String name, String address, String info, Event event) {
        this();
        this.name = name;
        this.address = address;
        this.info = info;
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
    
    

}
