package ch.jannis.gluecksrad.model;

import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int player_id;
    @Column(name = "player_name")
    private String player_name;
    private int player_balance;
    @Column(name = "player_mail")
    private String player_mail;
    @Column(name = "player_password")
    private String password;

    public String getName() {
        return player_name;
    }

    public void setName(String name) {
        this.player_name = name;
    }

    public int getBalance() {
        return player_balance;
    }

    public void setBalance(int balance) {
        this.player_balance = balance;
    }

    public String getMail() {
        return player_mail;
    }

    public void setMail(String mail) {
        this.player_mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }
}
