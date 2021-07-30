package it.niko.mywatchlist.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Watchlist")
@Table(name = "watchlist", schema = "mywatchlist")
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSerie", nullable = false)
    private Series series;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idStatus", nullable = false)
    private Status status;

    @Column(name = "progress")
    private int progress;

    @Column(name = "score")
    private int score;

    @Column(name = "comment")
    private String comment;
}
