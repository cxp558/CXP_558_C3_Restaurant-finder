package chris;

public class Item {
    private String name;// field that stores Item name, its private because we dont it access from another class
    private int price;// field that stores the Item price

    public Item(String name, int price) {// constructor is used to initialise the fields
        this.name = name;// initialise the field "name" to the parameter value received
        this.price = price;// initialise the field "price" to the parameter value received
    }

    public String getName() {// getter method that returns the name value
        return name;
    }

    public int getPrice() {// getter method that returns the price value
        return price;
    }

    @Override
    public String toString(){
        return  name + ":"
                + price
                + "\n"
                ;
    }
}
