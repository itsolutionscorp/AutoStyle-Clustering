#prime_factors.py
#prime_factors.py
import math

__author__ = 'greg'


def range(start, stop):
   i = start
   while i < stop:
       yield i
       i += 1


def prime_factors(natural_number):
    factors = []
    natural = natural_number
    while natural > 0:
        #naive linear search
        for i in range(2, natural_number+1):
            if natural % i == 0:
                natural /= i
                factors.append(i)
                break
        if natural == 2:
            natural = 0
            factors.append(2)
        if natural == 1:
            break
    return factors
