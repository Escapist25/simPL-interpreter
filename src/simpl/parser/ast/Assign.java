package simpl.parser.ast;

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

public class Assign extends BinaryExpr {

    public Assign(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return l + " := " + r;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult t1 = l.typecheck(E);
        TypeResult t2 = r.typecheck(t1.s.compose(E));
        
        TypeVar t4 = new TypeVar(true);
        RefType t3 = new RefType(t4);
        Substitution sout = t2.s.compose(t1.s);
        Substitution s1 = sout.apply(t1.t).unify(sout.apply(t3));
        sout = sout.compose(s1);
        Substitution s2 = sout.apply(t2.t).unify(sout.apply(t3.t));
        sout = sout.compose(s2);
        Type tout = Type.UNIT;
        return TypeResult.of(sout,tout);
        
        //throw new TypeMismatchError();
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        RefValue p1 = (RefValue)l.eval(s);
        Value p2 = r.eval(s);
        s.M.put(p1.p, p2);
        return Value.UNIT;
        //return null;
    }
}
