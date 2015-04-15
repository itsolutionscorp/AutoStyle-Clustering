def sum_of_multiples(n, factors=(3,5)):
    if 0 in factors:
        factors.remove(0)

    sum_of_mult = 0
    for num in range(n):
        for factor in factors:
            if num % factor == 0:
                sum_of_mult += num
                break
    return sum_of_mult
