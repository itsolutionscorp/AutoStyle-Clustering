import re
from operator import add, mul, floordiv, sub

OPS = {
    'plus': add,
    'minus': sub,
    'multiplied by': mul,
    'divided by': floordiv
}


def calculate(problem):
    p = problem[8:-1]
    oper = re.findall('[^-?\d]+', p)
    nums = map(int, re.findall('-?\d+', p))
    for op in oper:
        try:
            a, b, *nums = nums  # python 3 only
        except ValueError:
            b, *nums = nums
        try:
            a = OPS[op.strip()](a, b)
        except KeyError:
            raise ValueError("Invalid operator")
    return a
