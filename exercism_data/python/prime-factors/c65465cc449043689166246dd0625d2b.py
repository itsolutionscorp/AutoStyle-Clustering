def prime_factors(num):
    factors = []
    tmp = num
    count = 2
    
    while tmp > 1:
        while not tmp%count:
            # if not tmp in factors:
            factors.append(count)
            tmp /= count
        count += 1
    
    return factors
