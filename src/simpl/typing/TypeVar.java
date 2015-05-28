package simpl.typing;

import simpl.parser.Symbol;

public class TypeVar extends Type {

    private static int tvcnt = 0;

    private boolean equalityType;
    private Symbol name;

    public TypeVar(boolean equalityType) {
        this.equalityType = equalityType;
        name = Symbol.symbol("tv" + ++tvcnt);
    }

    @Override
    public boolean isEqualityType() {
        return equalityType;
    }

    @Override
    public Substitution unify(Type t) throws TypeCircularityError {
        // TODO
        //System.out.println(this);
        //System.out.println(t);
        //System.out.println(t.getClass());
        //    System.out.println("unify " + t + "        " + this);

        /* 3 cases:
         * a = a;//trivial
         * a = a type term include a ->type error
         * a = Type  //return substitution
         */
        if (t instanceof TypeVar){
            if (t.contains(this)){
                if (((TypeVar)t).name .equals( this.name))
                    return Substitution.IDENTITY;
                throw new TypeCircularityError();
            }
            if (!this.isEqualityType())
                return Substitution.of((TypeVar)t, this);
        }
        if (t.contains(this))
            throw new TypeCircularityError();
        return Substitution.of(this,t);
        //return null;
    }

    public String toString() {
        return "" + name;
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        if (tv.name.equals(name))
            return true;
        else
            return false;
        //return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        if (a.name .equals( name))
            return t;
        else 
            return this;
        //return null;
    }
}
