public class Division {
    private String name;
    private int id;

    /**
     * constructor for Division class objects that generates ids
     * @param name division name
     */
    public Division(String name)
    {
        this.name = name;
        char cname = name.toCharArray()[0];
        this.id = cname - 'A';
    }

    /**
     * returns division name
     * @return division name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * returns division id
     * @return division id
     */
    public int getId()
    {
        return this.id;
    }
}
