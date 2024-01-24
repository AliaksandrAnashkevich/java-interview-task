package com.hehnosky.javainterviewtask.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @SequenceGenerator(name = "operation_id_seq", sequenceName = "operation_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operation_id_seq")
    private Long id;

    @Column
    private LocalDateTime dateTime;

    @Column
    private BigDecimal amountPayment;

    @Column
    private BigDecimal amountAfter;

    @Column
    @Enumerated(EnumType.STRING)
    private OperationType type;

    @Column
    @Enumerated(EnumType.STRING)
    private OperationStatus status;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private BankAccount account;

}
