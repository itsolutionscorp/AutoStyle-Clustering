"""
sum_of_multiples - a module for summing FizzBuzz-esque series.
"""


def multiples(n, factors):
    """
    Create the set of the multiples of all the factors up to n.
    """

    # Use sets to efficiently merge the series given by each factor.
    mult_set = set()
    for factor in factors:
        if factor == 0:
            mult_set.add(0)
        else:
            mult_set = mult_set.union({i for i in xrange(factor, n, factor)})
    return mult_set


def sum_of_multiples(*args):
    """
    Sum the set generated by the multiples function.
    """
    
    # Parse the function arguments and sum the resulting set of multiples.
    n = args[0]
    if len(args) > 1:
        factors = args[1]
    else:
        factors = [3, 5]
    return sum(multiples(n, factors))