package simpl.parser.ast;

import simpl.interpreter.ConsValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.ListType;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeMismatchError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Cons extends BinaryExpr {

    public Cons(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " :: " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult t1 = l.typecheck(E);
        TypeResult t2 = r.typecheck(t1.s.compose(E));
        TypeVar tv = new TypeVar(true);
        ListType tl = new ListType(tv);
        Substitution sout = t2.s.compose(t1.s);
        Substitution s1 = sout.apply(t2.t).unify(sout.apply(tl));
        sout = sout.compose(s1);
        Substitution s2 = sout.apply(t1.t).unify(sout.apply(tl.t));
        sout = sout.compose(s2);
        return TypeResult.of(sout,sout.apply(t2.t));
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v1 = l.eval(s);
        Value v2 = r.eval(s);
        return new ConsValue(v1,v2);
        //return null;
    }
}
