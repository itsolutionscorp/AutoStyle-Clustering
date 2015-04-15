def sum_of_multiples(num, factors=[3, 5]):
    multiples = set()
    for fact in factors:
        if fact != 0:
            multiples.update(xrange(0, num, fact))

    return sum(multiples)
