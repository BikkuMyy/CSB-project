package sec.project.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Event extends AbstractPersistable<Long> {

    private String name;
    @OneToMany(mappedBy = "event")
    private List<Signup> signups = new ArrayList();

    public Event() {
        super();
    }

    public Event(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Signup> getSignups() {
        return signups;
    }

    public void addSignup(Signup signup) {
        this.signups.add(signup);
    }
    
    public void clearSignups(){
        this.signups = new ArrayList();
    }

}
