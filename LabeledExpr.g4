// Gramática extendida de ANTLR4 para calculadora científica
// Incluye funciones trigonométricas, logaritmos, raíz cuadrada, factorial,
// potencia, números reales y manejo de grados/radianes.


grammar LabeledExpr;


prog: stat+ ;


stat: expr NEWLINE # printExpr
| ID '=' expr NEWLINE # assign
| 'clear' NEWLINE # clear
| 'mode' '=' ('deg'|'rad') NEWLINE # setMode
| NEWLINE # blank
;


expr: expr op=('*'|'/') expr # MulDiv
| expr op=('+'|'-') expr # AddSub
| expr '^' expr # Power
| expr '!' # Factorial
| func=ID '(' expr ')' # Function
| ID # Id
| NUMBER # Number
| '(' expr ')' # Parens
;


// Tokens para IDs, números y saltos de línea
ID : [a-zA-Z]+ ;
NUMBER : [0-9]+ ('.' [0-9]+)? ;
NEWLINE : ('\r'? '\n')+ ;
WS : [ \t]+ -> skip ;
