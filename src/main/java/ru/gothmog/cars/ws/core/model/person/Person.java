package ru.gothmog.cars.ws.core.model.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import ru.gothmog.cars.ws.core.model.AbstractEntity;
import ru.gothmog.cars.ws.core.model.cars.Car;
import ru.gothmog.cars.ws.core.model.company.Company;
import ru.gothmog.cars.ws.core.model.company.RoleCompany;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "person", schema = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person extends AbstractEntity {

    @Column(name = "first_name", length = 150, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 150, nullable = false)
    private String lastName;
    @Column(name = "salary", precision = 12, scale = 2, nullable = false)
    private BigDecimal salary;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Car> cars;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false, foreignKey = @ForeignKey(name = "fk_person_company"))
    @JsonIgnore
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_company_id", nullable = false, foreignKey = @ForeignKey(name = "fk_person_role_company"))
    @JsonIgnore
    private RoleCompany roleCompany;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;
        return getId() != null && Objects.equals(getId(), person.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
