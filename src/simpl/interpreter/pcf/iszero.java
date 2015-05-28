package simpl.interpreter.pcf;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import simpl.interpreter.BoolValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.BooleanLiteral;
import simpl.parser.ast.Cond;
import simpl.parser.ast.Eq;
import simpl.parser.ast.Expr;
import simpl.parser.ast.IntegerLiteral;
import simpl.parser.ast.Name;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class iszero extends FunValue {

    public iszero() {
        // TODO
        super(Env.empty, Symbol.symbol("iszero argument"), 
            new Cond(
                new Eq(
                    new Name(Symbol.symbol("iszero argument")),
                    new IntegerLiteral(0)),
                new BooleanLiteral(true), 
                new BooleanLiteral(false)));
    }
}
