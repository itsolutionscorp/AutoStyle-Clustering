""" module sum of multiples for exercism.io programming training"""

def sum_of_multiples(limit=None, factors=[3,5]):
    factors = [i for i in factors if i != 0] # purge 0 from factors
    total = [i for i in range(limit) for j in factors if i % j == 0]
    total.append(0)    
    return sum(set(total))
