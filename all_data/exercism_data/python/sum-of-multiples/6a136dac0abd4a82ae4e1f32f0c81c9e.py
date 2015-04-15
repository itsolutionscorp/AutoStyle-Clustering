def sum_of_multiples(n, factors = [3,5]):
    multiples = set()
    for i in factors:
        if i==0:
            continue
        j = 1
        while i * j < n:
            multiples.add(i * j)
            j += 1
    return sum(multiples)
