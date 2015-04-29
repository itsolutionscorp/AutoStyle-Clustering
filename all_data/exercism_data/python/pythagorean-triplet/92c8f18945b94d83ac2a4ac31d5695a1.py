from math import sqrt, floor


def primitive_triplets(n):
    if n % 2:
        raise ValueError('Wrong Number!')

    return {tuple(sorted([j * j - i * i, n, j * j + i * i])) for i, j in factor_pair(n // 2) if (j - i) % 2}


def triplets_in_range(start, end):
    all_triplets = set()
    for i in range(4, end + 1, 4):
        for tr in primitive_triplets(i):
            all_triplets.update({tuple(map(lambda x: x * k, tr))
                                 for k in range(1, end // tr[2] + 1) if tr[0] * k >= start})

    return all_triplets


def is_triplet(tr):
    return min(tr) > 0 and sum(tr) > 2 * max(tr)


def factor_pair(n):
    ''' return all integer pairs (a, b) which a <= b and a * b = n '''
    return [(i, n // i) for i in range(1, floor(sqrt(n)) + 1) if n % i == 0]
