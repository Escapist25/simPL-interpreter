package simpl.parser.ast;

import jdk.nashorn.internal.runtime.regexp.joni.ApplyCaseFoldArg;
import simpl.interpreter.Env;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Rec extends Expr {

    public Symbol x;
    public Expr e;

    public Rec(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(rec " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeVar tv1 = new TypeVar(true);
        TypeResult t2 = e.typecheck(TypeEnv.of(E, x, tv1));
        Substitution sout = t2.s.compose(t2.t.unify(tv1));
        return TypeResult.of(t2.s,sout.apply(t2.t));
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Env e1 = new Env(s.E, x, new RecValue(s.E, x, e));
        return e.eval(State.of(e1, s.M, s.p));
        //return null;
    }
}
