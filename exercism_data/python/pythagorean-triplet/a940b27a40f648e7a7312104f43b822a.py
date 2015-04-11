def is_triplet(t):
    # return true regardless of order of elements
    result = False
    for i in range(2):
        if t[i%3]**2 + t[(i+1)%3]**2 == t[(i+2)%3]**2:
            result = True
    return result

def triplets_in_range(min, max):
    # iterate through all possible permutations in range
    result = set()
    max = max + 1
    for i in range(min, max):
        for j in range(min, max):
            for k in range(min, max):
                # check if a triplet
                if is_triplet((i, j, k)):
                    # store as set of tuples to avoid dupes
                    result.add(tuple(sorted([i, j, k])))
    return result
        
def primitive_triplets(b):
    # if b not divisible by 4 raise an error
    if b%4 != 0:
        raise ValueError("b not divisible by 4")
    # otherwise run though checks to find m and n
    result = set()
    for m in range(b+1):
        for n in range(b+1):
            # m is greater than n
            if m > n:
                # (m - n) is odd
                if (m - n) % 1 == 0:
                    # b = 2*m*n
                    if b == (2*m*n):
                        # m and n are coprime
                        is_coprime = True
                        for i in range(2, n+1):
                            if m % i == 0 and n % i == 0:
                                is_coprime = False
                        if is_coprime == True:
                            a = (m**2 - n**2)
                            c = (m**2 + n**2)
                            result.add(tuple(sorted([a, b, c])))
    return result
