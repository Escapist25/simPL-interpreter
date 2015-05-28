package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeMismatchError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        
        TypeResult t1 = l.typecheck(E);
        TypeResult t2 = r.typecheck(t1.s.compose(E));
        ArrowType t3 = new ArrowType(new TypeVar(true), new TypeVar(true));
        
    //System.out.println("int1.t:"+t1.s.compose(t2.s).apply(t1.t));
    //System.out.println("int2.t:"+t1.s.compose(t2.s).apply(t2.t));
    //System.out.println("int3:"+t1.s.compose(t2.s).apply(t3.t2));
        Substitution sout = t2.s.compose(t1.s);
        try{
        Substitution s1 = sout.apply(t1.t).unify(sout.apply(t3));
        sout = sout.compose(s1);
        }catch (TypeMismatchError e){
        }
        try{
        Substitution s2 = sout.apply(t2.t).unify(sout.apply(t3.t1));
        sout = sout.compose(s2);
        }catch (TypeMismatchError e){
        }
    //System.out.println("t1.t:"+t1.t);
    //System.out.println("t2.t:"+t2.t);
    //System.out.println("t3:"+t3);
    //System.out.println("outt1.t:"+sout.apply(t1.t));
    //System.out.println("outt2.t:"+sout.apply(t2.t));
    //System.out.println("outt3:"+sout.apply(t3.t2));
    //System.out.println("");
    //System.out.println("sout: "+sout);
        return TypeResult.of(sout,sout.apply(t3.t2));
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        FunValue fv = (FunValue)l.eval(s);
        Value v2 = r.eval(s);
        return fv.e.eval(State.of(new Env(fv.E, fv.x, v2),s.M,s.p));
        //return null;
    }
}
