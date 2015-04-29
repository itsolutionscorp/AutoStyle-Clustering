import re

REPLACEMENTS = (("plus", "+"),
                ("minus", "-"),
                ("multiplied by", "*"),
                ("divided by", "//"))


def calculate(expression):
    try:
        expression = re.search(r"What is(.+)\?", expression).group(1)
        for word, symbol in REPLACEMENTS:
            expression = expression.replace(word, symbol)
        return _evaluate_start_to_end(expression.split())
    except:
        raise ValueError("Cannot evaluate expression: " + repr(expression))


def _evaluate_start_to_end(terms):
    if len(terms) == 3:
        return eval(" ".join(terms))
    else:
        start = str(_evaluate_start_to_end(terms[:3]))
        return _evaluate_start_to_end([start] + terms[3:])
