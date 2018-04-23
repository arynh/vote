package cs125.mp7.vote;

/**
 * This is the file in which we need to get the data from
 * the API and store it somehow.
 */
public class CardData {

    private String name;
    private String party;
    private String office;

    public CardData(String name, String party, String office) {
        this.name = name;
        this.party = party;
        this.office = office;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}
