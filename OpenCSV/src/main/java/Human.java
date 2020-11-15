public class Human {

    private String id;
    private String name;
    private String gender;
    private String salary;
    private String birtDate;
    private Division division;
    public

    /**
     * Constructor for Human class objects
     */
    Human(String id, String name, String gender, String salary, String birthday, String divname)
    {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        birtDate = birthday;
        division = new Division(divname);
    }

    /**
     * returns id
     * @return id
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * returns name
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * returns gender
     * @return gender
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * returns salary
     * @return salary
     */
    public String getSalary() {
        return this.salary;
    }

    /**
     * returns bd
     * @return Birth date
     */
    public String getBirtDate() {
        return this.birtDate;
    }

    /**
     * returns division name
     * @return Division name
     */
    public String getDivisionName() {
        return division.getName();
    }

    /**
     * returns division id
     * @return Division id
     */
    public int getDivisionId()
    {
        return division.getId();
    }

}
