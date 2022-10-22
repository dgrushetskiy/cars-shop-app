package ru.gothmog.cars.ws.core.model.cars;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import ru.gothmog.cars.ws.core.model.AbstractEntity;

import javax.persistence.Index;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "type_car", schema = "cars", indexes = @Index(name = "type_car_type_name_key", columnList = "type_name", unique = true))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TypeCar extends AbstractEntity {

    @Column(name = "type_name", length = 100, nullable = false)
    private String typeName;
    @OneToMany(mappedBy = "typeCar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Car> cars;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TypeCar typeCar = (TypeCar) o;
        return getId() != null && Objects.equals(getId(), typeCar.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
