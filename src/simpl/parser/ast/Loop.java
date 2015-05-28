package simpl.parser.ast;

import java.awt.print.Printable;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Loop extends Expr {

    public Expr e1, e2;

    public Loop(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(while " + e1 + " do " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult t1 = e1.typecheck(E);
        //System.out.println("loop t1:" + t1.t);
        TypeResult t2 = e2.typecheck(t1.s.compose(E));
        //System.out.println("loop t2:" + t2.t);
        Substitution sout = t1.s.compose(t2.s.compose(t1.t.unify(Type.BOOL)));
        return TypeResult.of(sout,Type.UNIT);
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        BoolValue v1 = (BoolValue)e1.eval(s);
        //System.out.println(this);
        //System.out.println((new Deref(new Name(Symbol.symbol("b")))).eval(s));
        //System.out.println(e1.eval(s));
        if (v1.b == true){
            Value v2 = e2.eval(s);
            return (new Loop(e1,e2)).eval(s);
        }else
            return Value.UNIT;
        
        //return null;
    }
}
