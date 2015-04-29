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


# defines product of items in a sequence
prod = lambda seq: seq[0] * prod(seq[1:]) if len(seq) > 0 else 1


def largest_product(digits,num_factors):
    """Given a str of digits, returns the largest product a slice can return"""
    if min(len(digits),num_factors) == 0: # manage zero-length arguments.
        return 1  # which makes not much sense to me. ( see prod() )
    return max( prod(sl) for sl in slices(digits,num_factors) )
