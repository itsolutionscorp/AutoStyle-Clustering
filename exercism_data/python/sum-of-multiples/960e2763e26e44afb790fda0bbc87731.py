def sum_of_multiples(upperbound, factors = [3, 5]):
    multiples = []
    for i in range(1, upperbound): 
        for m in factors:
            if m > 0 and i % m == 0 and i not in multiples: 
                multiples.append(i)
            elif m == 0:
                multiples.append(0)
    return sum(multiples)
