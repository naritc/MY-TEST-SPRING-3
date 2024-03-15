package my.tiran.ctable.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reserve_detail")
public class ReserveDetailEntity {
    @Id
    @Column(name = "reserve_detail_id")
    @GeneratedValue
    private Long reserveDetailId;

    @ManyToOne
    @JoinColumn(name = "reserve_id")
    public ReserveEntity reserve;

    @Column(name = "reserve_name")
    private String reserveName;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "reserve_datetime")
    private LocalDateTime reserveDateTime;

    @Column(name = "exit_datetime")
    private LocalDateTime exitDateTime;

    @Column(name = "number_customer")
    private Integer numberCustomer;

    @Column(name = "created_datetime")
    private LocalDateTime createdDatetime;
}
