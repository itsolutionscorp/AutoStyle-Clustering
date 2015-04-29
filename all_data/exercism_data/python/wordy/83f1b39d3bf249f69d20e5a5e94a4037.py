'''exer wordy'''

# The exercise should have used a made-up operator like 'flubber' and not
# an easily implemented operator like xor.  XOR'ing to ints or long ints
# is possible with Python.

OPS = {'times': lambda operands: operands[0] * operands[1],
       'plus': lambda operands: operands[0] + operands[1],
       'minus': lambda operands: operands[0] - operands[1],
       'divided_by': lambda operands: operands[0] / operands[1],
       # 'xor': lambda operands: operands[0]^operands[1],
       }

def calculate(mathq):
    '''calculate the result of the mathematical question mathq'''
    # guard and normalize
    if not mathq.startswith('What is '):
        raise ValueError("Not a formal question.")
    mathq = mathq.replace('What is', '')
    if not mathq.endswith('?'):
        raise ValueError("Not a well formed question.")
    mathq = mathq[:-1]

    # normalize multi-token ops
    mathq = mathq.replace('divided by', 'divided_by')
    mathq = mathq.replace('multiplied by', 'times')

    # process the remaining normalized mathq, no operator precedence required
    # so a simple direct parsing, with a single register, is all that is
    # required for the
    #   operand operator operand [operator operand]... structure that remains
    register = 0
    is_operand = True
    operator = OPS['plus']
    for token in mathq.split():
        if is_operand:
            register = operator((register, int(token)))
            is_operand = not is_operand
        else:
            try:  # guard against unknown operators
                operator = OPS[token]
                is_operand = not is_operand
            except KeyError:
                raise ValueError('Unimplemented Operator: %s' % token)


    return register
