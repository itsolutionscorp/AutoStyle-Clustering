def sum_of_multiples(top, factors=None):
    try:
        factor_set = set(factors)
    except TypeError:
        factor_set = {3, 5}
    # 0 is not a valid factor
    try:
        factor_set.remove(0)
    except KeyError:
        pass

    return sum(n for n in range(top) if any(n % d == 0 for d in factor_set))
