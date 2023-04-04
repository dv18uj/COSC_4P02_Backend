import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "waypoint")
public class Waypont implements Serializable
{
    private static final long serialVersionUID = 338L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wid;

    //The foreign key of the project
    @Column(name = "sid", nullable = false)
    private int sid;

    //The foreign key of the project
    @Column(name = "pid", nullable = false)
    private int pid;

    @Column(name = "x", nullable = false)
    private int x;

    @Column(name = "y", nullable = false)
    private int y;

    @Column(name = "z", nullable = false)
    private int z;

    public int getWid() { return wid; }
    public int getPid() { return pid; }
    public int getSid() { return sid; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }
}