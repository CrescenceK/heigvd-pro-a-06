package heigvd.ch.segfaultapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.xml.bind.v2.TODO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table (name = "Message")
public class Message {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer messageId;

    @Getter
    @Column(name = "contenu")
    @NonNull
    private String contenu;

    @Getter
    @Setter
    @Column(name = "score")
    //@NonNull //Il mettait un warning :o ?
    private int score = 0;

    @Getter
    @Setter
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "super_message_id", referencedColumnName = "message_id")
    private Message superMessageId; // Todo, is it worth using?

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "Message_Family",
            joinColumns = @JoinColumn(name ="message_parent"),
            inverseJoinColumns = @JoinColumn(name = "message_fils")
    )
    private Set<Message> messageSet;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "message")
    private Set<Vote> voteSet;

    /*

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "Vote",
            joinColumns = @JoinColumn(name ="message_id"),
            inverseJoinColumns = @JoinColumn(name = "utilisateur_id")
    )
    Set<Utilisateur> utilisateurSet;
*/
    // TODO: 2019-04-04 Corriger les attributs qui sont des relations 


}
