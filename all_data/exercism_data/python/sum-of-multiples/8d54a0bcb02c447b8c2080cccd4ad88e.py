def sum_of_multiples(n, lt=[3, 5]):
    ret = 0
    for i in range(n):
        for x in lt:
            if x <> 0 and i % x == 0:
                ret += i
                break
    return ret
