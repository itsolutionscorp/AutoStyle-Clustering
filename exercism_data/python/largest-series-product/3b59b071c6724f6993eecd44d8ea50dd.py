from functools import reduce

def slices(series, length):
    if length < 0 or length > len(series): raise ValueError
    return [ [int(series[y+x]) for x in range(length)] 
                               for y in range(len(series)-length+1) ]

def product(seq):
    return reduce(lambda x, acc: acc*x, seq, 1)

def largest_product(seq, length):
    return max( product(s) for s in slices(seq, length))
