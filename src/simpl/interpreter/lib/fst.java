package simpl.interpreter.lib;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.sun.xml.internal.bind.v2.runtime.Name;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;
import sun.net.www.content.text.plain;

public class fst extends FunValue {

    public fst() {
        // TODO
        super(Env.empty, Symbol.symbol("fst argument"),new Expr(){
            
    public  TypeResult typecheck(TypeEnv E) throws TypeError{
        return TypeResult.of(new TypeVar(true));
    }

    public Value eval(State s) throws RuntimeError{
        PairValue p = (PairValue) s.E.get(Symbol.symbol("fst argument"));
        return p.v1;
    }
        });
    }
}
