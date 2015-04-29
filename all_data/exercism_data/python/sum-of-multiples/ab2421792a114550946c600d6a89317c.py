def sum_of_multiples(limit, factors = [3, 5]):
    multiples = set()
    for factor in factors:
        if factor < 1:
            continue
        multiples = multiples.union(range(factor, limit, factor))
    return sum(multiples)
