def parse_what(word, result, mode):
    if word != 'What': raise ValueError
    return (parse_is, result, mode)
    
def parse_is(word, result, mode):
    if word != 'is': raise ValueError
    return (parse_int, result, 'is')

def parse_by(word, result, mode):
    if word != 'by': raise ValueError
    return (parse_int, result, mode)

def parse_int(word, result, mode):
    if mode == 'is': result = int(word)
    elif mode == 'plus': result += int(word)
    elif mode == 'minus': result -= int(word)
    elif mode == 'multiplied': result *= int(word)
    elif mode == 'divided': result /= int(word)
    else: raise ValueError
    return (parse_operation, result, None)

def parse_operation(word, result, mode):
    if word in ['multiplied', 'divided']:
        return (parse_by, result, word)
    return (parse_int, result, word)

def calculate(question):
    if not question.endswith('?'): raise ValueError
    question = question[:-1].split()
    parser, result, mode = parse_what, 0, None
    while len(question) > 0:
        parser, result, mode = parser(question[0], result, mode)
        question = question[1:]
    return result
