package simpl.typing;

public final class ListType extends Type {

    public Type t;

    public ListType(Type t) {
        this.t = t;
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        return this.t.isEqualityType();
        //return false;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        if (t instanceof TypeVar) {
            return t.unify(this);
        }
        ListType t1 = (ListType) t;
        if (t instanceof ListType) 
            return t1.t.unify(this.t);
        throw new TypeMismatchError();
        //return null;
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        return this.t.contains(tv);
        //return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        return new ListType(this.t.replace(a, t));
        //return null;
    }

    public String toString() {
        return t + " list";
    }
}
