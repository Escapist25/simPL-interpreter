package simpl.interpreter;

public class PairValue extends Value {

    public final Value v1, v2;

    public PairValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        return "pair@" + v1 + "@" + v2;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (other instanceof PairValue){
            PairValue t1 = (PairValue) other;
            return t1.v1.equals(this.v1) && t1.v2.equals(this.v2); 
        }
        return false;
    }
}
