def sum_of_multiples(limit, bases = [3, 5]):
    0 in bases and bases.remove(0)
    return sum(i for base in bases 
                    for i in range(base, limit, base) 
                        if not any(1 for b in bases if b < base and i % b == 0))
