package com.smartcity.location.village;

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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "village")
public class VillageEntity {
    @Id
    @Column("id")
    private String id;
    @Column("name")
    private String name;
    @Column("city_id")
    private String cityId;
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
