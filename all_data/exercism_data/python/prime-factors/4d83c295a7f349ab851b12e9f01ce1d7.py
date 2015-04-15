def prime_factors(num):
    ret = []
    i = 2
    while i < num + 1:
        while num % i == 0:
            ret.append(i)
            num = int(num / i)
        i += 1
    return ret
