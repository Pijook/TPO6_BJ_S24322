package pl.pijok.tpo6_bj_s24322.lib;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SearchCriteria {

    private String title;
    private String author;
    private String description;

}
