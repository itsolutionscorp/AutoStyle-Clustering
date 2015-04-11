__author__ = 'banarasitippa'

def sum_of_multiples(N,factors=[3,5]):
    '''
    following assumptions about the inputs
    * All input numbers are non-negative 'int's, i.e. natural numbers including
      zero.
    * If a list of factors is given, its elements are uniqe and sorted in
      ascending order.
    * If the 'factors' argument is missing, use the list [3, 5] instead.
    :param N: int
    :param factors: list of ints
    :return:
    '''


    return sum([ product for product in range(N) if [factor for factor in factors if factor and not (product%factor)] ])

print (sum_of_multiples(1000))
