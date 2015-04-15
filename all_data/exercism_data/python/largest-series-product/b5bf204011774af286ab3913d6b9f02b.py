# Largest series product Python exercism, 1st iteration

def slices(s, n):
    """ Returns a list of lists of length n of consecutive numbers in a string s """
    if n > len(s):
        raise ValueError('Cannot have slice longer than string length')
    elif n <= 0:
        raise ValueError('Slice must be of positive length')
    else:
        series = []
        j=0
        while j + n <= len(s):
            series.append([int(s[i]) for i in xrange(j,j+n)])
            j += 1 

        return series
        
def prod(numbers):
    """ Returns the product of a list of numbers """
    prod = 1
    for number in numbers:
        prod *= number
    return prod

def largest_product(s,n):
    """ Returns the maximum product of n consecutive integers in a string s"""
    if n == 0:
        return 1
    else:
        series = slices(s,n)
        products = [prod(slice) for slice in slices(s,n)]
        return max(products)
