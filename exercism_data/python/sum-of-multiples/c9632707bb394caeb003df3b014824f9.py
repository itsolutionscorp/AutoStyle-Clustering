def sum_of_multiples(limit, factors=[3, 5]):
    multiple_sum = 0
    for x in range(1, limit):
        for factor in factors:
            try:
                if x % factor == 0:
                    multiple_sum += x
                    break
            except ZeroDivisionError:
                pass
    return multiple_sum
