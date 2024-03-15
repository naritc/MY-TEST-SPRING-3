package my.tiran.ctable.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reserve")
public class ReserveEntity {
    @Id
    @Column(name = "reserve_id")
    @GeneratedValue
    private Long reserveId;

    @Column(name = "reserve_date")
    private LocalDateTime reserveDate;

    @Column(name = "created_datetime")
    private LocalDateTime createdDatetime;

    @OneToMany(mappedBy = "reserve")
    private List<ReserveDetailEntity> reserveDetails;

}
