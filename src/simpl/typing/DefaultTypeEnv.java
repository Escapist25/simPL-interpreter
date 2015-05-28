package simpl.typing;

import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {

    private TypeEnv E;

    public DefaultTypeEnv() {
        // TODO
        E = new TypeEnv() {
            
            @Override
            public Type get(Symbol x) {
                if (x.toString().equals("fst")){
                    TypeVar t1 = new TypeVar(true);
                    TypeVar t2 = new TypeVar(true);
                    return new ArrowType(new PairType(t1, t2), t1);
                }else if (x.toString().equals("snd")){
                    TypeVar t1 = new TypeVar(true);
                    TypeVar t2 = new TypeVar(true);
                    return new ArrowType(new PairType(t1, t2), t2);
                }else if (x.toString().equals("hd")){
                    TypeVar t1 = new TypeVar(true);
                    return new ArrowType(new ListType(t1), t1);
                }else if (x.toString().equals("tl")){
                    TypeVar t1 = new TypeVar(true);
                    return new ArrowType(new ListType(t1), t1);
                }else if (x.toString().equals("iszero")){
                    return new ArrowType(Type.INT, Type.BOOL);
                } else if (x.toString().equals("pred")){
                    return new ArrowType(Type.INT, Type.INT);
                } else if (x.toString().equals("succ")){
                    return new ArrowType(Type.INT, Type.INT);
                } 


                    return null;
                // TODO Auto-generated method stub
                //return null;
            }
        };
    }

    @Override
    public Type get(Symbol x) {
        return E.get(x);
    }
}
