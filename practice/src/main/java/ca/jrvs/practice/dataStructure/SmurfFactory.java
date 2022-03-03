package ca.jrvs.practice.dataStructure;

// LinkedIn Learning Static Exercise
public class SmurfFactory {
    public static void main(String[] args) {
        Smurf papa = new Smurf("papa");
        papa.printName();
        papa.createSmurf("henry");
        papa.printName();
        papa.createSmurf("papa");
        papa.createSmurf("papa");
//        Smurf papa = Smurf.createSmurf("papa");

//        Smurf papa2 = Smurf.createSmurf("papa");


    }
}
