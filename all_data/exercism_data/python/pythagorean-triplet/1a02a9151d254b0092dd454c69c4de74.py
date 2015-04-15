def is_triplet(triplet):
    a, b, c = sorted(triplet)
    return a**2 + b**2 == c**2

def triplets_in_range(min, max):
    # iterate through all possible permutations in range
    result = set()
    max = max + 1
    for a in range(min, max):
        for b in range(min, max):
            for c in range(min, max):
                # check if a triplet
                triplet = tuple(sorted((a, b, c)))
                if is_triplet(triplet):
                    result.add(triplet)
    return result
        
def primitive_triplets(b):
    # if b not divisible by 4 raise an error
    if b%4 != 0:
        raise ValueError("b not divisible by 4")
    # otherwise run though checks to find m and n
    result = set()
    for m in range(b+1):
        for n in range(b+1):
            # if m is greater than n AND (m - n) is odd AND  b = 2*m*n
            if m > n and (m - n) % 1 == 0 and b == (2*m*n):
                # and m and n are coprime
                is_coprime = True
                for i in range(2, n+1):
                    if m % i == 0 and n % i == 0:
                        is_coprime = False
                if is_coprime == True:
                    a = (m**2 - n**2)
                    c = (m**2 + n**2)
                    result.add(tuple(sorted((a, b, c))))
    return result
