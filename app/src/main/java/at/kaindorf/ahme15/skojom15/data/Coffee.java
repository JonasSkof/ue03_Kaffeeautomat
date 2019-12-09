package at.kaindorf.ahme15.skojom15.data;

public enum Coffee {


    ESPRESSO("Espresso",0.55),
    ESPRESSOS("Espresso Macchiato",0.60),
    CAPPUCCINO("Cappuccino",0.60),
    LATTE("Latte",0.60),
    MOCHA("Mocha",0.70),
    AMERICAN("Amreicano",0.60),
    MELANGE("Melange",0.60),
    IRISHER("Irish Coffee",0.60);


    private String name;
    private double price;

    Coffee(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}






