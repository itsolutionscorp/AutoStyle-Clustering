# Sum of multiples Python Exercism, 1st iteration

def is_multiple_of(k,l):
    """ Returns True if k is a multiple of l, and False if not """
    if l == 0:
        return False
    else:
        return k % l == 0

def sum_of_multiples(n,m = [3,5]):
    """ Returns sum of all multiples of the numbers in m which are less than n """
    mults = [i for i in xrange(n) if any(is_multiple_of(i,number) for number in m)]    
    return sum(mults)
