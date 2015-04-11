def sum_of_multiples(n, factors=[3, 5]):
    multiples = set()
    for f in factors:
        if f > 0: multiples.update(i for i in range(f, n, f))

    return sum(multiples)
