#!/usr/bin/python
# exercism python 15: largest series product

from operator import mul

def slices(series, length):
    if len(series) < length or length < 1:
        raise ValueError, 'The series can\'t be shorter than the length!'
    answer = [item for item in slice_gen(series, length) if len(item) == length]
    return answer

def slice_gen(series, length):
    for index in xrange(0, len(series)):
        yield [int(item) for item in series[index:index+length]]
        
def largest_product(series, length):
    if not (series or length):
        return 1
    if len(series) < length:
        raise ValueError, 'The series can\'t be shorter than the length!'
    return max([reduce(mul, slice) for slice in slices(series, length)])
        
