import math

def primitive_triplets(b):
    if b % 4 != 0:
        raise ValueError
    candidates = triplets_with_lhs(b) | triplets_with_rhs(b)
    return set(filter(is_primitive_triplet, candidates))

def triplets_in_range(min, max):
    def all_in_range(triplet):
        return all([min <= x and x <= max for x in triplet])
    result = set([])
    for c in range(min, max + 1):
        result |= set(filter(all_in_range, triplets_with_rhs(c)))
    return result

def is_triplet(t):
    if len(t) != 3:
        return False
    a, b, c = sorted(t)
    return a ** 2 + b ** 2 == c ** 2

def triplets_with_lhs(a):
    result = set([])
    a_square = a ** 2
    # c ** 2 - b ** 2 = a ** 2 implies (c + b)(c - b) = a ** 2
    # c + b and c - b are divisors of a ** 2
    for i in range(1, a):
        if a_square % i == 0:
            # b - c in [1, a), b + c in (a, a ** 2]
            # b + c = a ** 2 / i, b - c = i
            b = int((a_square / i + i) / 2)
            c = b - i
            result.add(tuple(sorted([a, b, c])))
    return result

def triplets_with_rhs(c):
    result = set([])
    c_square = c ** 2
    for a in range(1, c):
        b = int(math.sqrt(c_square - a ** 2))
        if is_triplet(tuple([a, b, c])):
            result.add(tuple(sorted([a, b, c])))
    return result

def is_primitive_triplet(t):
    def is_coprime(t):
        def gcd(a, b, c):
            def gcd(a, b):
                return a if b == 0 else gcd(b, a % b)
            return gcd(a, gcd(b, c))
        a, b, c = t
        return gcd(a, b, c) == 1
    return len(t) == 3 and is_triplet(t) and is_coprime(t)
