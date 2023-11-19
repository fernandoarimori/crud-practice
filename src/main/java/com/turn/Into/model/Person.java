package com.turn.Into.model;

import com.turn.Into.model.dto.PersonPostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity(name = "Person")
@Table(name = "tb_person")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String register;
    private Integer age;
    private BigDecimal weight;
    @Enumerated(EnumType.STRING)
    private WRank wRank;
    private String image;
    private Boolean active;

    public Person(PersonPostDTO dto) {
        this.name = dto.name();
        this.register = dto.register();
        this.age = dto.age();
        this.weight = dto.weight();
        this.wRank = this.setWRank(this.weight);
        this.image = dto.image();
        this.active = true;
    }

    public void  softDelete(){
        this.active = false;
    }

    public void update(PersonPostDTO dto) {
        this.name = dto.name();
        this.register = dto.register();
        this.age = dto.age();
        this.weight = dto.weight();
        this.wRank = this.setWRank(this.weight);
        this.image = dto.image();
    }

    private WRank setWRank(BigDecimal weight) {
        if (weight.compareTo(new BigDecimal(71.00)) == -1) {
            return WRank.LIGHT;
        }
        if (weight.compareTo(new BigDecimal(81.00)) == -1) {
            return WRank.REGULAR;
        }
        if (weight.compareTo(new BigDecimal(101.00)) == -1) {
            return WRank.HEAVY;
        }

        return WRank.EXTRA_HEAVY;
    }
}

