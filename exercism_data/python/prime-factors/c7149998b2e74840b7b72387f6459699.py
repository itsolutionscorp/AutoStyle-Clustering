def prime_factors(num):
    x = 2
    factors = []
    
    while True:
        if num % x == 0:
            factors.append(x)
            num = num / x
        else:
            x += 1
            if x > num:
                return factors       
    return
