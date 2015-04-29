import numpy


'''
    returns a list of lists with all possible substrings of 
        length n
'''
def slices(numbers, n):
    length = len(numbers)
    if not (numbers and n):
        return [[1]]

    if n > length or not n:
        raise ValueError
    return [ [int(x) for x in list(numbers[i:i+n])] \
        for i in range(length) if i <= length-n ]


def largest_product(numbers, n):
    return max([ numpy.prod(x) for x in slices(numbers,n) ])
    
