package simpl.parser.ast;

import simpl.typing.ListType;
import simpl.typing.PairType;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeMismatchError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult t1 = l.typecheck(E);
        TypeResult t2 = r.typecheck(t1.s.compose(E));
        /*
        TypeVar tv = new TypeVar(true);
        Substitution sout = t2.s.compose(
            t1.t.unify(Type.INT).compose(
            t2.t.unify(Type.INT)));
        Type tout = Type.INT;
        return TypeResult.of(sout,tout);
        */

        if (t1.t.isEqualityType() && t2.t.isEqualityType()){
            //Substitution sout = t1.s.compose(t2.s.compose(t1.t.unify(t2.t)));
        Substitution sout = t2.s.compose(t1.s);
        Substitution s1 = sout.apply(t1.t).unify(sout.apply(t2.t));
        sout = sout.compose(s1);
            //System.out.println(sout);
            return TypeResult.of(sout,Type.BOOL);
        }
        throw new TypeMismatchError();
        //return null;
    }
}
