from operator import *

op_map = {'plus': add,
          'minus': sub,
          'multiplied': mul,
          'divided': truediv
          }

def calculate(problem):
    tokens = problem.strip('?').replace('by', '').split()
    tokens.remove("What")
    tokens.remove("is")
    while len(tokens) > 1:
        b = int(tokens.pop(2))
        try:
            op = op_map[tokens.pop(1)]
        except KeyError:
            raise ValueError('Invalid operation.')
        a = int(tokens.pop(0))
        tokens.insert(0, op(a,b))
    return tokens[0]
