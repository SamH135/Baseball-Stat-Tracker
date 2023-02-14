public class Player implements Comparable<Player>{

    // attributes to hold data directly from file input
    public String name;
    boolean homeTeam;
    int hit;
    int out;
    int strikeout;
    int walk;
    int hitBy;
    int sacrifice;
    int error;

    public int getHit()
    {
        return hit;
    }
    public int getWalk()
    {
        return walk;
    }
    public int getStrikeout()
    {
        return strikeout;
    }
    public int getHitBy()
    {
        return hitBy;
    }

    // methods that return derived data when it's needed

    Player() // default constructor
    {
        name = "null";
        homeTeam = false;
        hit = 0;
        out = 0;
        strikeout = 0;
        walk = 0;
        hitBy = 0;
        sacrifice = 0;
    }

    Player(String name, boolean homeTeam, int hit, int out, int strikeout, int walk, int hitBy, int sacrifice, int error) // overloaded constructor
    {
        this.name = name;
        this.homeTeam = homeTeam;
        this.hit = hit;
        this.out = out;
        this.strikeout = strikeout;
        this.walk = walk;
        this.hitBy = hitBy;
        this.sacrifice = sacrifice;
        this.error = error;
    }



    public void updatePlayer(int hit, int out, int strikeout, int walk, int hitBy, int sacrifice, int error)
    {
        this.hit += hit;
        this.out += out;
        this.strikeout += strikeout;
        this.walk += walk;
        this.hitBy += hitBy;
        this.sacrifice += sacrifice;
        this.error += error;
    }

    public int getAtBat()
    {
        return hit + out + strikeout;       // returns number of at bats for player
    }

    public int getPlateAppearance()
    {
        return hit + out + strikeout + walk + hitBy + sacrifice;
    }

    public double getOnBasePercent()
    {
        if(getPlateAppearance() == 0)
        {
            return 0.000;
        }
        return ((double)(hit + walk + hitBy))/((double)(getPlateAppearance()));
    }

    public double getBattingAvg()
    {
        if(getAtBat() == 0)
        {
            return 0.000;
        }
        return ((double)(hit))/((double)(getAtBat()));
    }

    @Override
    public String toString()
    {
        return "" + name + "\t" + getAtBat() + "\t" + hit + "\t" + walk 
        + "\t" + strikeout + "\t" + hitBy + "\t" + sacrifice + "\t" 
        + String.format("%,.3f", getBattingAvg()) + "\t" + String.format("%,.3f", getOnBasePercent()) + "\n";
    }

    @Override
    public int compareTo(Player obj) {
        if(this.name.compareTo(obj.name) < 0)
        {
            return -1;
        }
        else if(this.name.compareTo(obj.name) > 0)
        {
            return 1;
        }
        return 0;       // the names are equal
    }



}   // end of Player class

