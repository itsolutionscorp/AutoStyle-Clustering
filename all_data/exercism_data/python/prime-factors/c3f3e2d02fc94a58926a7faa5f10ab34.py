def prime_factors(num):
    remain = num
    i = 2
    result = []
    while remain > 1:
        if remain % i == 0:
            result.append(i)
            remain /= i
        else:
            i += 1
    return result
