package at.kaindorf.ahme15.skojom15.data;

public enum Coffee {


    ESPRESSO("Espresso",55),
    ESPRESSOS("Espresso Macchiato",60),
    CAPPUCCINO("Cappuccino",60),
    LATTE("Latte",60),
    MOCHA("Mocha",70),
    AMERICAN("Amreicano",60),
    MELANGE("Melange",60),
    IRISHER("Irish Coffee",60);



    private String name;
    private int price;

    Coffee(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}






