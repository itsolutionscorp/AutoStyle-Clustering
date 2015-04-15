def sum_of_multiples(num, factors=[3, 5]):
    multiples = []
    for i in range(1, num):
        for j in factors:
            if j and i % j == 0:
                multiples.append(i)
                break
    return sum(multiples)
