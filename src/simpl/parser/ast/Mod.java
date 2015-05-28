package simpl.parser.ast;

import java.awt.print.Printable;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class Mod extends ArithExpr {

    public Mod(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " % " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        IntValue v1 = (IntValue)l.eval(s);
        IntValue v2 = (IntValue)r.eval(s);
        if (v2.n == 0)
            throw new RuntimeError("divided by 0!");
        //System.out.println(""+v1 + "%"+ v2 + "="+v1.n%v2.n);
        //System.out.println("xxxxxx");
        return new IntValue(v1.n%v2.n);
        //return null;
    }
}
