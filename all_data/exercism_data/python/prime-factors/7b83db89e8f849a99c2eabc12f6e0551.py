def prime_factors(num):
    factors = []
    fac = 2
    while fac**2<=num:
        while num%fac == 0:
            factors.append(fac)
            num/=fac
        fac+=1
    if num > 1:
        factors.append(num)
    return factors
