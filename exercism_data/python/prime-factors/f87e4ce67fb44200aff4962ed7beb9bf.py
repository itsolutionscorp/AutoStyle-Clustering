def prime_factors(num):
    factors = []
    working = True
    while working:
        working = False
        for i in range(2, int(num**0.5)+2):
            if num%i == 0:
                factors.append(i)
                num/=i
                working = True
                break
    if num > 1:
        factors.append(num)
    return factors
