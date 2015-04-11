import math
from fractions import gcd

def _iseven(i):
    return (i % 2) == 0

def _iscoprime(m,n):
    if reduce(gcd,(m,n)) == 1:
        return True
    return False
    
def _primitivemn(m, n):
    diff = m-n
    return diff > 0 and (diff % 2) == 1 and _iscoprime(m,n)

def _getvalidmn(b):
    b /= 2
    allmn = []
    for n in range(1, int(math.ceil(math.sqrt(b)))):
        m, mod = divmod(b,n)
        if not mod: 
            allmn.append((m,n))
    return allmn
    
def primitive_triplets(b):
    if not b % 4 == 0:
        raise ValueError("Not a valid value for b = ", b)
    return set([_triplet(m,n,b) for m,n in _getvalidmn(b) if _primitivemn(m,n)])
    
def _triplet(m,n,b):
    geta = lambda m,n: pow(m,2) - pow(n,2)
    getc = lambda m,n: pow(m,2) + pow(n,2)
    return (min(geta(m,n),b), max(geta(m,n),b), getc(m,n))

def _getalltriplets(b):
    return [_triplet(m,n,b) for m,n in _getvalidmn(b)]
    
def is_triplet(triplet):
    trip = triplet[0] if (triplet[0] % 4) == 0 else triplet[1]
    if trip % 4 == 0:
        return set(triplet) in map(set,_getalltriplets(trip))
    return False

def triplets_in_range(min, max):
    inrange = []
    # Highest value will be <= square root of the range maximum
    for m in range(1,int(math.sqrt(max))):
        for n in range(1,m):
            if _primitivemn(m,n):
                b = 2 * m * n
                trip =  _triplet(m,n,b)
                a,b,c = trip
                i = 1
                while c <= max:
                    cotrip = tuple(map(lambda x: x*i, trip))
                    a,b,c = cotrip
                    if a >= min and c <= max:
                        inrange.append(cotrip)
                    i += 1
    return set(inrange)
