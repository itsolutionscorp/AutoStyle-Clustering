def prime_factors(n):
    result = []
    currentfactor = 2
    while n > 1:
        while n % currentfactor == 0:
            result.append(currentfactor)
            n  = n / currentfactor
        if currentfactor == 2:
            currentfactor = 3
        else:
            currentfactor += 2
    return result
