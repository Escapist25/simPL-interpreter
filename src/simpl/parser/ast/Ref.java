package simpl.parser.ast;

import simpl.interpreter.Int;
import simpl.interpreter.IntValue;
import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult t1 = e.typecheck(E);
        return TypeResult.of(t1.s,new RefType(t1.s.apply(t1.t)));
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Int p = new Int(s.p.get());
        s.p.set(s.p.get()+1);
        Value v1 = e.eval(s);
        s.M.put(p.get(), v1);
        return new RefValue(p.get());
        
        //return null;
    }
}
