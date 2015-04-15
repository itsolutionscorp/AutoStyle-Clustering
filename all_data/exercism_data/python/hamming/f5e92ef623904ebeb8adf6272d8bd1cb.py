import itertools
import operator

def distance(a, b):
    return sum(itertools.starmap(operator.ne, zip(a, b)))
