from functools import reduce
from operator import mul

def slices(text, n):
    if n > len(text):
        raise ValueError("Can't make slices longer than the text")
    return [list(map(int,text[s:s+n])) for s in range(0,len(text)-n+1)]

def product(numbers):
    return reduce(mul, numbers)

def largest_product(text, n):
    if text == "":
        if n==0:
            return 1
        else:
            raise ValueError("Can't make slices longer than the text")
    largest_slice = max(slices(text,n), key=product)
    return product(largest_slice)
