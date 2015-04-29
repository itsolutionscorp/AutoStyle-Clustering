# largest-series-product exercise

def slices(in_string, n):
    """returns all possible slices converted to lists of ints."""
    #check arguments
    if not 0 <= n <= len(in_string):
        raise ValueError('Length of slice is wrong')
    if not all( c in "0123456789" for c in in_string):
        raise ValueError('Can slice only strings representing numbers')

    if in_string == "" or n == 0:  # manage 0-length slices
        return []
    return [list(map(int,in_string[i:i+n])) for i in range(len(in_string)-n+1)]

def product(factors):
    # factors is an iterable of items providing a __mul__  or __rmul__ method
    """mutiply items in an iterable"""
    if len(factors) == 0:  # manage zero-length arguments.
        return 1
    if len(factors) == 1:
        return factors[0]
    else:
        return factors[0] * product(factors[1:])  # recursive call

def largest_product(digits,num_factors):
    """Given a str of digits, returns the largest product a slice can return"""
    if min(len(digits),num_factors) ==0: # manage zero-length arguments.
        return 1  # which makes not much sense to me either. ( see product() )
    return max( product(sl) for sl in slices(digits,num_factors) )
