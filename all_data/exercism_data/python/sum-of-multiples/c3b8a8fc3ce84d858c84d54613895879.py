def sum_of_multiples(candidate, factors=[3, 5]):
    multiples = set()
    for number in xrange(1, candidate):
        for f in factors:
            if f != 0 and number % f == 0:
                multiples.add(number)
    return sum(multiples)
    # multiples = set(number for number in xrange(1, candidate) for f in factors if f != 0 and number % f == 0)
