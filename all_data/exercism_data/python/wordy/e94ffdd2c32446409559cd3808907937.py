OPERATORS = {'plus': '+', 'minus': '-', 'multiplied by': '*', 'divided by': '/'}

def calculate(phrase):
    # strip off extraneous characters
    phrase = phrase[8:-1]
    # substitute text operators for symbols
    for text, symbol in OPERATORS.items():
        phrase = phrase.replace(text, symbol)
    # split on whitespace into a list
    lst = phrase.split()
    
    try:
        # store the first number in result
        result = lst.pop(0)
        # loop through the list by 2s
        for i in range(0, len(lst), 2):
            # eval the string: result + lst[i+1] + lst[i+2]
            result = str(eval(result + ' '.join(lst[i:i+2])))
    except (SyntaxError):
        raise ValueError
    
    return int(result)
