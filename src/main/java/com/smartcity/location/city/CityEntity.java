package com.smartcity.location.city;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "city")
public class CityEntity {
    @Id
    @Column("id")
    private String id;
    @Column("name")
    private String name;
    @CreatedDate
    @Column("created_at")
    private Instant createdAt;
    @Column("updated_at")
    @LastModifiedDate
    private Instant updatedAt;
    @Version
    @Column("etag")
    private Long etag;

}
