import operator
import re

from functools import reduce


def calculate(question):
    return reduce(lambda v, p: p[0](v, p[1]), _op_number_pairs(question), 0)


def _op_number_pairs(question):
    return [(_function(m.group(1)), int(m.group(2)))
            for m in re.finditer(r'([^\d\-]+)(-?\d+)', question)]


def _function(text):
    text = text.strip()
    if text == 'What is' or text == 'plus':
        return operator.add
