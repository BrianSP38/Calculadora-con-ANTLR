import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.IOException;


// Programa principal para ejecutar la calculadora en Java
public class Calc {
public static void main(String[] args) throws IOException {
// Leer de archivo si se pasa argumento, si no desde consola
CharStream input = CharStreams.fromStream(System.in);


// Lexer y Parser de ANTLR
LabeledExprLexer lexer = new LabeledExprLexer(input);
CommonTokenStream tokens = new CommonTokenStream(lexer);
LabeledExprParser parser = new LabeledExprParser(tokens);


ParseTree tree = parser.prog();


// Visitor para evaluar expresiones
EvalVisitor eval = new EvalVisitor();
eval.visit(tree);
}
}
