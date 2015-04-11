import re

def calculate(expr):
    parse = (
        ('plus',          '+'),
        ('minus',         '-'),
        ('multiplied by', '*'),
        ('divided by',    '/'),
        ('What is ',      ''),
        ('?',             ''))

    for this, that in parse:
        expr = expr.replace(this, that)

    expr = re.sub(r'([\*\+\-\/]*[0-9]+\s+[\*\+\-\/]+\s+[0-9]+)', r'(\1)', expr)

    try:
        return eval(expr)
    except Exception:
        raise ValueError
