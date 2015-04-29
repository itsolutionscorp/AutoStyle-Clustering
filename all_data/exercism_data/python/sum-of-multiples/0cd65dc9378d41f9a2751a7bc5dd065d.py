def sum_of_multiples(upper, denoms=[3, 5]):
    multiples = set()
    for n in denoms:
        if n == 0:
            continue
        for i in xrange(1, upper / n + 1):
            multiple = n * i
            if multiple < upper:
                multiples.add(multiple)
    return sum(multiples)
