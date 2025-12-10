package Lab_1;

public class ConcreteStrategySwim implements Strategy{
    @Override
    public String move() {
        return "I'm swimming!";
    }
}
