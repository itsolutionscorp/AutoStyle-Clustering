from fractions import gcd
from math import ceil
from math import sqrt

def triplets_in_range(min, max):
    """Return a list of Pythagorean Triplets whose members
    are in the given range.
    """
    triplets = set()
    for a in range(min, max-1):
        for b in range(a+1, max):
            c= sqrt(a**2 + b**2)
            if (c == int(c)) and (c<=max):
                triplets.add((a,b,int(c)))
    return triplets

def is_triplet(triplet):
    """Test a given 3-element tuple to see if it is a pythagorean
    triplet
    """
    (a,b,c) = sorted(triplet)
    return a**2 + b**2 == c**2

def primitive_triplets(b):
    """Finds all primitive pythagorean triplets that have a given value
    as one part of the triplet. The given value must be divisble by 4.
    """

    #First, eliminate invalid cases where b isn't divisble by 4
    if b%4 != 0:
        raise ValueError("Cannot find triplets from this value.")

    #Next, find all the factor pairs for the value b/2
    factor_pairs = []
    b_half = b//2
    for i in range(1, ceil(sqrt(b_half))):
        if b_half%i == 0:
            factor_pairs.append((i, b_half//i))

    #Now find factor pairs (n,m) that fit m-n is odd and gcd(m,n)==1
    coprime_pairs = []
    for (n,m) in factor_pairs:
        if ((m-n)%2==1) and (gcd(m,n)==1):
            coprime_pairs.append((n,m))

    #Finally build the pythagorean triple using b and the coprime pairs
    triplets = set()
    for (n, m) in coprime_pairs:
        a = m**2-n**2
        c = m**2+n**2
        triplets.add(tuple(sorted((a,b,c))))

    return triplets
