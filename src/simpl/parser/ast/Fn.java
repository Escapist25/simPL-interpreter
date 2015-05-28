package simpl.parser.ast;

import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Fn extends Expr {

    public Symbol x;
    public Expr e;

    public Fn(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(fn " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeVar tv1 = new TypeVar(true);
        TypeResult t2 = e.typecheck(TypeEnv.of(E, x, tv1));
 //System.out.println("fn: "+t2.s.apply(tv1) + "-> " + t2.s.apply(t2.t));
 //System.out.println(t2.s);
        return TypeResult.of(t2.s,new ArrowType(t2.s.apply(tv1),t2.s.apply(t2.t)));
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return new FunValue(s.E,x,e);
        //return null;
    }
}
