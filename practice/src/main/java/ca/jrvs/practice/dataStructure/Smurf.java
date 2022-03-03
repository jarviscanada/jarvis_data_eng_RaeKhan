// LinkedIn Learning Static Exercise
public class Smurf {

    //	private String name;
    static String name;

    //	public static Smurf createSmurf(String name) {
    //		System.out.println("Creating " + name + " Smurf");
    //		return new Smurf(name);
    //	}

    //	private Smurf(String name) {
    //		this.name = name;
    //	}
    public Smurf(String name) {
        Smurf.name = name;
    }

    public static Smurf createSmurf(String name) {
        if (Smurf.name != name) {
            System.out.println("Creating " + name + " Smurf");
        }
        return new Smurf(name);
    }

    public void printName() {
        System.out.println("My name is " + name + " Smurf.");
    }

    public void eat() {
        System.out.println(name + " Smurf is eating Smurfberries.");
    }

}