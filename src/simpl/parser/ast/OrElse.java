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

public class OrElse extends BinaryExpr {

    public OrElse(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " orelse " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult t1 = l.typecheck(E);
        TypeResult t2 = r.typecheck(t1.s.compose(E));
        Substitution sout = t2.s.compose(t1.s);
        Substitution s1 = sout.apply(t1.t).unify(Type.BOOL);
        sout = sout.compose(s1);
        Substitution s2 = sout.apply(t2.t).unify(Type.BOOL);
        sout = sout.compose(s2);
        Type tout = Type.BOOL;
        return TypeResult.of(sout,tout);
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        BoolValue v1 = (BoolValue)l.eval(s);
        if (v1.b == true)
            return new BoolValue(true);
        BoolValue v2 = (BoolValue)r.eval(s);
        if (v2.b == true)
            return new BoolValue(true);
        return new BoolValue(false);
        //return null;
    }
}
