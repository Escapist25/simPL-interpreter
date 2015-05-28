package simpl.parser.ast;

import javax.swing.undo.StateEdit;

import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Name extends Expr {

    public Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return "" + x;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        if (E.get(x) == null){
            System.out.println("variable name not found!");
            throw new TypeError("Variable Name Not Found!\n");
        }
        return TypeResult.of(Substitution.IDENTITY,E.get(x));
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v1 = s.E.get(x);
        if (v1 instanceof RecValue){
            RecValue vv1 = (RecValue)v1;
            Rec r = new Rec(vv1.x, vv1.e);
            return r.eval(State.of(vv1.E, s.M, s.p) );
        }
        return v1;
        //return null;
    }
}
