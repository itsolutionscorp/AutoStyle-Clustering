operators = {'plus':'__add__', 'minus':'__sub__', 'mult':'__mul__', \
             'div':'__floordiv__', }

def calculate(words):
    words = parse_input(words)
    try:
        for i in range(0,len(words)-1,2):
            a,op,b = int(words[i]), words[i+1], int(words[i+2])
            if op not in operators:
                raise ValueError
            total = getattr(a, operators[op])(b)
            words[i+2] = total
    except ValueError():
        return 'Invalid question or missing number/operation.'
    
    return total

def parse_input(words):
    words = words.replace('multiplied by', 'mult')
    words = words.replace('divided by', 'div')
    return [x for x in words[8:-1].split()]
