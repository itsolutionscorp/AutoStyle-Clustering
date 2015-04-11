operations = { 'is':         lambda r,x: x,
               'plus':       lambda r,x: r+x,
               'minus':      lambda r,x: r-x,
               'multiplied': lambda r,x: r*x,
               'divided':    lambda r,x: r/x }

def parse_what(word, result, mode):
    if word != 'What': raise ValueError
    return (parse_operation, result, mode)

def parse_by(word, result, mode):
    if word != 'by': raise ValueError
    return (parse_int, result, mode)

def parse_operation(word, result, mode):
    if word in ['multiplied', 'divided']:
        return (parse_by, result, word)
    return (parse_int, result, word)

def parse_int(word, result, mode):
    if mode not in operations: raise ValueError
    result = operations[mode](result, int(word))
    return (parse_operation, result, None)

def calculate(question):
    if not question.endswith('?'): raise ValueError
    parser, result, mode = parse_what, 0, None
    for word in question[:-1].split():
        parser, result, mode = parser(word, result, mode)
    return result
