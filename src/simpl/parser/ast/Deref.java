package simpl.parser.ast;

import java.awt.print.Printable;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeMismatchError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Deref extends UnaryExpr {

    public Deref(Expr e) {
        super(e);
    }

    public String toString() {
        return "!" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult t1 = e.typecheck(E);
        RefType tr = new RefType(new TypeVar(true));
        Substitution sout = t1.s.compose(t1.t.unify(tr));
        //System.out.println("deref t:"+sout.apply(tr));
        return TypeResult.of(sout,sout.apply(tr.t));
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        RefValue p1 = (RefValue)e.eval(s);
        Value v = s.M.get(p1.p);
        return v;
        //return null
    }
}
