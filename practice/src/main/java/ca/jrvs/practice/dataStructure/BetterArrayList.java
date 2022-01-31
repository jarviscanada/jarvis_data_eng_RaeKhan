import java.util.ArrayList;

// LinkedIn Learning Inheritance Exercise 1
public class BetterArrayList extends ArrayList {

    ArrayList<Integer> nums = new ArrayList<Integer>();
    public void push(int i) {
        nums.add(i);
        System.out.println(nums);
    }
    public void pop(int i) {
        nums.remove(i);
        System.out.println(nums);
    }


}