from fractions import gcd

def primitive_triplets(b):
    if b % 2 == 1:
        raise ValueError
    pairs = coprime_factors(b/2)
    triplets = set()
    for m, n in pairs:
        if (m - n) % 2 == 1 and m > n:
            a = m**2 - n**2
            c = m**2 + n**2
            trip = [a, b, c]
            trip.sort()
            triplets.add(tuple(trip))
    return triplets

def triplets_in_range(rmin, rmax):
    triplets = []
    for b in range(rmax):
        if b % 4 == 0:
            b_trips = primitive_triplets(b)
            for trip in b_trips:
                for k in range(rmin/min(trip), rmax/max(trip) + 1):
                    tlist = [k*x for x in trip]
                    if min(tlist) >= rmin and max(tlist) <= rmax:
                        triplets.append(tuple(tlist))
    return set(triplets)

def is_triplet(trip):
    t = list(trip)
    t.sort()
    c = t.pop()  # Pop largest number
    a, b = t
    return a**2 + b**2 == c**2

def coprime_factors(x):
    # Return all coprime pairs (m,n) such that x = m*n
    pairs = []
    for factor in xrange(1, x/2+1):
        if x % factor == 0:
            n = factor
            m = x / factor
            if is_coprime(m, n):
                pairs.append((m, n))
    return pairs

def is_coprime(m, n):
    return gcd(m, n) == 1
