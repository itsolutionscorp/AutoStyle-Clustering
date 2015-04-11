def sum_of_multiples(n, factors=[3,5]):
    
    multiples = []
    for factor in factors:
        if factor == 0:
            continue
        for i in range(0, (n-1)/factor+1):
            if i*factor not in multiples:
                multiples.append(i*factor)

    return sum(multiples)
