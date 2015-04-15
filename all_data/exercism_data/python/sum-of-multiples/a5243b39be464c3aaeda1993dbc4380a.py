def sum_of_multiples(num, factors=[3, 5]):
    multiples = set()
    for f in factors:
        i = 1
        while f and ((i * f) < num):
            multiples.add(i * f)
            i += 1
    return sum(multiples)
