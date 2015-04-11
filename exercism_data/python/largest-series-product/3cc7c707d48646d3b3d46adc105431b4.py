from operator import mul


def slices(digits, n):
    if n > len(digits): raise ValueError
    
    the_slices = []
    for x in xrange(n, len(digits)+1):
        the_slices.append(map(int,list(digits[x-n:x])))
    return the_slices
    

def largest_product(digits, n):
    if n > len(digits): raise ValueError
    if len(digits) == 0 and n == 0: return 1
    
    max_sum = 0
    for x in slices(digits, n):
        max_sum = max(max_sum, reduce(mul, x))
    
    return max_sum
