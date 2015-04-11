""" module sum of multiples for exercism.io programming training"""


"""
You can make the following assumptions about the inputs to the
'sum_of_multiples' function:
    * All input numbers are non-negative 'int's, i.e. natural numbers including
      zero.
    * If a list of factors is given, its elements are uniqe and sorted in
      ascending order.
    * If the 'factors' argument is missing, use the list [3, 5] instead.
"""

def sum_of_multiples(limit=None, factors=[3,5]):
    factors = [i for i in factors if i != 0] # purge 0 from factors
    total = [i for i in range(limit) for j in factors if i % j == 0]
    total.append(0)    
    return sum(set(total))
