from math import sqrt, floor


def primitive_triplets(n):
    if n % 2:
        return ()

    return {(j * j - i * i, n, j * j + i * i) for i, j in factor_pair(n // 2) if (j - i) % 2}

def triplets_in_range():
    return 0

def is_triplet():
    return False

def factor_pair(n):
    ''' return all integer pairs (a, b) which a <= b and a * b = n '''
    return [(i, n // i) for i in range(1, floor(sqrt(n)) + 1) if n % i == 0]
