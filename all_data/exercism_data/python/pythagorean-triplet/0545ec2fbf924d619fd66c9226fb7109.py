import math

def primitive_triplets(comp):
    if comp % 4 != 0:
        raise ValueError("b is required to be divisible by 4")

    b_half = comp / 2
    max_n = int(math.ceil(math.sqrt(b_half)))
    mn_combos = set()

    for n in range(1, max_n):
        if b_half % n == 0:
            m = b_half / n

            if m % 2 == n % 2:
                # both m and n are odd or even
                continue

            m_factors = factors(m)
            n_factors = factors(n)
            intersection = m_factors.intersection(n_factors)
            intersection.discard(1)
            if len(intersection) > 0:
                # numbers not coprime, discard
                continue

            mn_combos.add((m,n))

    triplets = set()
    for m, n in mn_combos:
        a = m**2 - n**2
        b = 2 * m * n
        c = m**2 + n**2
        triplets.add((min(a, b), max(a, b), c))
    return triplets

def triplets_in_range(min, max):
    # go over each possible b from 4 to max
    # this gets us our possible primitive triplets
    primitives = set()
    for b in range(4, max+1, 4):
        primitives.update(primitive_triplets(b))

    triplets = set()
    for primitive in primitives:
        k = 1
        while True:
            triplet = tuple( k * comp for comp in primitive )

            # primitives are already sorted,
            # so we only need to check the first and last index for range
            if triplet[2] > max:
                # k has gotten too big, this triplet can't be used anymore
                break

            if triplet[0] >= min:
                # c <= max already tested from above
                triplets.add(triplet)

            k += 1

    return triplets

def is_triplet(triplet):
    a, b, c = sorted(triplet)
    return a**2 + b**2 == c**2

def factors(num):
    factors = set([1])

    for i in range(2, int(math.ceil(num / 2))):
        if num % i == 0:
            factors.add(i)

    factors.add(num)
    return factors
