'''exer Pythagorean triples'''

from fractions import gcd

def possible_ac(num):
    '''find a and c where num = 2mn'''
    found = []
    # given that 2mn = b, or in our case num, solve for mn
    mn = num/2
    # m must be > than n, so start iterating through candidates
    for n in range(1, mn**2):   # upper bound is a guestimation
        for m in range(n, mn**2):
            if m * n == mn:     # must satisfy the 2mn=b equation
                # result of m-n must be odd, and the gcd(m,n) is 1 if co-prime
                if (m - n) % 2 and gcd(m, n) == 1:
                    # generate the other 2 sides using Euclidean formula
                    triplet = [m**2 - n**2, m**2 + n**2, num]
                    triplet.sort()
                    found.append(tuple(triplet))
    return found

def primitive_triplets(num):
    '''return the primitive triplets where num is a, b or c'''
    if num % 4:
        raise ValueError
    return set(possible_ac(num))

def triplets_in_range(low, high):
    '''generate all triplets in range'''
    trips = []
    high_plus1 = high + 1  # account for range being 0 based
    for a in range(low, high_plus1 - 2):
        for b in range(a, high_plus1 - 1):
            for c in range(b, high_plus1):
                if is_triplet((a, b, c)):
                    trips.append((a, b, c))
    return set(trips)

def is_triplet(abc):
    '''return true if is Pythagorean triplet'''
    # square the inputs, then sort so that asq < bsq < csq so only 1 comparison
    asq, bsq, csq = [x**2 for x in sorted(abc)]

    return asq + bsq == csq   # or asq + csq == bsq or bsq + csq == asq
