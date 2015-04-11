#!/usr/bin/python
def slices(sequence, n):
    if n > len(sequence):
        raise(ValueError)
    else:
        return filter(lambda x: len(x) == n, [[int(y) for y in list(sequence)][x:x+n] for x in range(len(sequence))])
    
def largest_product(sequence, n):
    sequence_slices = slices(sequence, n)
    product = lambda l: reduce(lambda x, y: x * y, l, 1) 
    return max([product(l) for l in sequence_slices]) if sequence_slices != [] else 1
