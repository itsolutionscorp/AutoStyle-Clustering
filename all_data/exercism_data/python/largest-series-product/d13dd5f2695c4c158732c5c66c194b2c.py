#import reduce
from functools import reduce
import operator

def product(iterable):
    # same as sum(iterable)
    return reduce(operator.mul, iterable, 1)

def largest_product(series,size):
    return max([ product(v) for v in slices(series,size) ])
    
def slices(series, size):
    if size > len(series): raise ValueError("len!")
    digits = [ int(d) for d in series ]
    return [list(digits[i:i+size]) for i in range(0, len(digits)+1-size) ]
