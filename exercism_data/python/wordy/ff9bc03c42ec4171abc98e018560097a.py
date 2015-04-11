
OPERATORS = {'plus': '+', 'minus': '-', 'multiplied by': '*', 'divided by': '/'}

def calculate(string):
    string = string[8:-1]
    try:
        for old, new in OPERATORS.items():
            string = string.replace(old, new)
        tokens = string.split()
        while len(tokens) >= 3:
            tokens[:3] = [str(eval(' '.join(tokens[:3])))]
        return eval(tokens[0])
    except (SyntaxError, NameError):
        raise ValueError
