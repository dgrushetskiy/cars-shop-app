package ru.gothmog.cars.ws.core.model.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import ru.gothmog.cars.ws.core.model.AbstractEntity;
import ru.gothmog.cars.ws.core.model.person.Person;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "company", schema = "cars", indexes = @Index(name = "company_company_name_key", columnList = "company_name", unique = true))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Company extends AbstractEntity {

    @Column(name = "company_name", length = 150, nullable = false)
    private String companyName;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Person> persons;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Company company = (Company) o;
        return getId() != null && Objects.equals(getId(), company.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
