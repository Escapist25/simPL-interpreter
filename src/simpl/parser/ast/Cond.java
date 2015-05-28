package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Cond extends Expr {

    public Expr e1, e2, e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult t1 = e1.typecheck(E);
        TypeResult t2 = e2.typecheck(t1.s.compose(E));
        TypeResult t3 = e3.typecheck(t2.s.compose(t1.s.compose((E))));
        Substitution sout = t3.s.compose(t2.s.compose(t1.s));
        /*
    System.out.println("condint1.t:"+sout.apply(t1.t));
    System.out.println("condint2.t:"+sout.apply(t2.t));
    System.out.println("condint3.t:"+sout.apply(t3.t));
    */
        Substitution s2 = sout.apply(t1.t).unify(Type.BOOL);
        sout = sout.compose(s2);
        Substitution s1 = sout.apply(t2.t).unify(sout.apply(t3.t));
        sout = sout.compose(s1);
        /*
    System.out.println("condoutt1.t:"+sout.apply(t1.t));
    System.out.println("condoutt2.t:"+sout.apply(t2.t));
    System.out.println("condoutt3.t:"+sout.apply(t3.t));
    */
        Type tout = sout.apply(t3.t);
        return TypeResult.of(sout,tout);
        
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        BoolValue v1 = (BoolValue)e1.eval(s);
        Value v2;
        if (v1.b == true)
            v2 = e2.eval(s);
        else {
            v2 = e3.eval(s);
        }
        return v2;
        //return null;
    }
}
