package simpl.typing;

public final class RefType extends Type {

    public Type t;

    public RefType(Type t) {
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
        if (t instanceof RefType) {
            RefType t1 = (RefType) t;
            return this.t.unify(t1.t);
        }
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
        return new RefType(this.t.replace(a, t)); 
        //return null;
    }

    public String toString() {
        return t + " ref";
    }
}
