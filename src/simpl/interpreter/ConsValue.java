package simpl.interpreter;

public class ConsValue extends Value {

    public final Value v1, v2;

    public ConsValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        // TODO
        return "cons("+v1+","+v2+")";
        //return null;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (other instanceof ConsValue){
            ConsValue t1 = (ConsValue) other;
            return t1.v1.equals(this.v1) && t1.v2.equals(this.v2); 
        }
        return false;
    }
}
