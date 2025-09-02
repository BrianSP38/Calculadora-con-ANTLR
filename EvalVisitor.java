import java.util.*;
return visit(ctx.expr());
}


// Suma y resta
@Override
public Double visitAddSub(LabeledExprParser.AddSubContext ctx) {
double left = visit(ctx.expr(0));
double right = visit(ctx.expr(1));
return ctx.op.getType() == LabeledExprParser.PLUS ? left + right : left - right;
}


// Multiplicación y división
@Override
public Double visitMulDiv(LabeledExprParser.MulDivContext ctx) {
double left = visit(ctx.expr(0));
double right = visit(ctx.expr(1));
return ctx.op.getText().equals("*") ? left * right : left / right;
}


// Potencia
@Override
public Double visitPower(LabeledExprParser.PowerContext ctx) {
double base = visit(ctx.expr(0));
double exp = visit(ctx.expr(1));
return Math.pow(base, exp);
}


// Factorial
@Override
public Double visitFactorial(LabeledExprParser.FactorialContext ctx) {
double val = visit(ctx.expr());
return factorial((int) val);
}


private double factorial(int n) {
if (n <= 1) return 1;
return n * factorial(n - 1);
}


// Funciones matemáticas (sin, cos, sqrt, etc.)
@Override
public Double visitFunction(LabeledExprParser.FunctionContext ctx) {
String func = ctx.func.getText();
double arg = visit(ctx.expr());


// Convertir a radianes si estamos en modo grados
if (useDegrees && (func.equals("Sin") || func.equals("Cos") || func.equals("Tan"))) {
arg = Math.toRadians(arg);
}


switch (func) {
case "Sin": return Math.sin(arg);
case "Cos": return Math.cos(arg);
case "Tan": return Math.tan(arg);
case "Sqrt": return Math.sqrt(arg);
case "Ln": return Math.log(arg);
case "Log": return Math.log10(arg);
case "Rad": return Math.toRadians(arg);
case "Deg": return Math.toDegrees(arg);
default: return 0.0;
}
}
}
