package by.kuropatin.fraud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "FraudCheckHistory")
@Table(indexes = {
        @Index(name = "idx_fraudcheckhistory", columnList = "customerId"),
        @Index(name = "idx_fraudcheckhistory", columnList = "isFraudster")
})
public class FraudCheckHistory {

    @Id
    @SequenceGenerator(name = "fraud_id_generator", sequenceName = "fraud_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "fraud_id_generator")
    private Long id;
    private Long customerId;
    private Boolean isFraudster;
    private LocalDateTime created;
}