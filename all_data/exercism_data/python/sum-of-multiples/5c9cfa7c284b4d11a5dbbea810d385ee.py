def sum_of_multiples(n: int, factors=(3, 5)):
    multiples = set()

    for factor in factors:
        if factor > 0:
            multiples.update(i for i in range(factor, n, factor))

    return sum(multiples)
