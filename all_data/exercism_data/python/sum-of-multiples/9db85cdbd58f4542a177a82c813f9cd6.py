__author__ = 'Hinek'

def sum_of_multiples(limit, factors=[3, 5]):
    sum = 0
    for i in xrange(limit):
        for f in factors:
            if f>0 and i%f == 0:
                sum += i
                break
    return sum
