# Sieve

def sieve(limit):
    numbers_range = [True] * (limit + 1)
    results = []
    
    for i in range(2, limit + 1):
        if numbers_range[i] == True:
            multiplier = 2
            while i * multiplier <= limit:
                numbers_range[i * multiplier] = False
                multiplier += 1
    
    for j in range(2, limit + 1):
        if numbers_range[j] == True:
            results.append(j)
        
    return results
