package entities;

public class Division implements IDivision {
    private Integer id;
    private String name;
    private static Integer GUID = 0;

    public Division(String name) {
        this.id = GUID++;
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
