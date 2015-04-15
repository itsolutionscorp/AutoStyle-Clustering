def prime_factors(num):
    result = []
    n = 2
    while num > 1:
        if num % n == 0:
            result.append(n)
            num = num / n
        else:
            n += 1
    return result
