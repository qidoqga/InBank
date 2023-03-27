public class DecisionEngine {

    public float amount;
    public boolean decision;

    public DecisionEngine(boolean decision, float amount) {
        this.decision = decision;
        this.amount = amount;
    }

    public boolean getDecision() {
        return this.decision;
    }

    public float getAmount() {
        return this.amount;
    }

    public static DecisionEngine decideLoan(String personalCode, float amount, int period) {
        int creditModifier = getCreditModifier(personalCode);
        double creditScore = (creditModifier / amount) * period;

        if (creditScore < 1 || amount < 0 || period < 12 || period > 60) {
            return new DecisionEngine(false, 0);
        } else {
            int maxAmount = (int)Math.floor(creditScore * amount);
            if (maxAmount > 10000) {
                maxAmount = 10000;
                return new DecisionEngine(true, maxAmount);
            } else if (maxAmount < 2000) {
                return new DecisionEngine(false, 0);
            } else {
                return new DecisionEngine(true, maxAmount);
            }
        }
    }

    public static int getCreditModifier(String personalCode) {
        switch (personalCode) {
            case "49002010965" -> {
                return 0;
            }
            case "49002010976" -> {
                return 100;
            }
            case "49002010987" -> {
                return 300;
            }
            case "49002010998" -> {
                return 1000;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        DecisionEngine loan = decideLoan("49002010987", 5000, 20);
        System.out.println(loan.getDecision() + " " + loan.getAmount());

    }
}
