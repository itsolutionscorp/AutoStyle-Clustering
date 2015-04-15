import itertools
import operator
import functools

def sum_of_multiples(limit, factors=[3,5]):
    non_zero_factors = list(f for f in factors if f != 0)
    total = sum(m for factor in non_zero_factors for m in range(factor, limit, factor))

    '''
        The code lines below remove the multiples of the prime factors that get counted
    '''
    factor_multiples_to_remove =(itertools.combinations(non_zero_factors, num_of_factors) for num_of_factors in range(2,len(non_zero_factors)+1))
    for factor_tuples in factor_multiples_to_remove:
        for factor_tuple in factor_tuples:
            factor_tuple_product = functools.reduce(operator.mul,list(factor_tuple))
            total -= sum(n for n in range(factor_tuple_product, limit, factor_tuple_product) )
    return total
