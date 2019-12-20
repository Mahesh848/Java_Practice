class Dog {
    private int size;

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    Dog (int size) {
        this.size = size;
    }

    public void bark() {
        if (size > 60) {
            System.out.println("WooooF!..");            
        } else if(size > 14) {
            System.out.println("Ruuuf!..");
        } else {
            System.out.println("Yip!..");
        }
    }
}
class TestDogBarking {
    public static void main(String[] args) {
        Dog b1 = new Dog(62);
        b1.bark();
        Dog b2 = new Dog(18);
        b2.bark();
        Dog b3 = new Dog(4);
        b3.bark();
    }
}