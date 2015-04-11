from operator import mul

def slices(numStr, n):

    if n > len(numStr) or n < 0:
        raise ValueError("This is a meaningful error message.")

    return [[int(intc) for intc in numStr[i:i+n]] for i in xrange(len(numStr)-n+1)]


def largest_product(numStr, n):

    return max(reduce(mul,s,1) for s in slices(numStr,n))
