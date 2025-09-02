import math
return 0


# Cambiar modo de ángulos
def visitSetMode(self, ctx):
mode = ctx.getChild(2).getText()
self.useDegrees = (mode == "deg")
print("Modo cambiado a:", mode)
return 0


# Variables
def visitId(self, ctx):
id = ctx.ID().getText()
return self.memory.get(id, 0)


# Números
def visitNumber(self, ctx):
return float(ctx.NUMBER().getText())


# Paréntesis
def visitParens(self, ctx):
return self.visit(ctx.expr())


# Suma y resta
def visitAddSub(self, ctx):
left = self.visit(ctx.expr(0))
right = self.visit(ctx.expr(1))
if ctx.op.type == LabeledExprParser.PLUS:
return left + right
return left - right


# Multiplicación y división
def visitMulDiv(self, ctx):
left = self.visit(ctx.expr(0))
right = self.visit(ctx.expr(1))
if ctx.op.text == '*':
return left * right
return left / right


# Potencia
def visitPower(self, ctx):
return math.pow(self.visit(ctx.expr(0)), self.visit(ctx.expr(1)))


# Factorial
def visitFactorial(self, ctx):
return factorial(int(self.visit(ctx.expr())))


# Funciones matemáticas
def visitFunction(self, ctx):
func = ctx.func.text
arg = self.visit(ctx.expr())


if self.useDegrees and func in ("Sin", "Cos", "Tan"):
arg = math.radians(arg)


if func == "Sin": return math.sin(arg)
if func == "Cos": return math.cos(arg)
if func == "Tan": return math.tan(arg)
if func == "Sqrt": return math.sqrt(arg)
if func == "Ln": return math.log(arg)
if func == "Log": return math.log10(arg)
if func == "Rad": return math.radians(arg)
if func == "Deg": return math.degrees(arg)
return 0
