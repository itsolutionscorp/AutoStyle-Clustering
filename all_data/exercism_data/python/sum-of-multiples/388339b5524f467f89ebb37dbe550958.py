def sum_of_multiples(limit, factors=[3, 5]):
    result = 0
    while 0 in factors:
        factors.remove(0)
    for i in range(limit):
        for f in factors:
            if i % f == 0:
                break
        else:
            continue
        # If you've reached here, it's a multiple
        result += i
    return result
