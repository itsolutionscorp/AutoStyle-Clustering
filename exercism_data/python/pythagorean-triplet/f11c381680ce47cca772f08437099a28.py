import math


def is_triplet(ls):
    """
    Tells whether or not the input list is a pythagorean triplet.
    """
    hyp = max(ls)
    side1 = min(ls)
    side2 = sum(ls) - side1 - hyp
    if hyp**2 - side1**2 - side2**2 == 0:
        return True
    else:
        return False


def primitive_triplets(b):
    """
    Lists the primitive pythagorean triplets which include b.
    """
    if b % 4 != 0:
        raise ValueError("Please give an input that is divisible by 4")
    triplets = set()
    for i in range(1,b):
        for j in range(i,b):
            if 2 * i * j == b and i != j and relatively_prime(i,j):
                triplets.add(tuple(sorted((j**2-i**2, 2 * i * j, i**2 + j**2))))
    return triplets
     

def triplets_in_range(low,high):
    """
    Lists all pythagorean triplets between low and high.
    """
    triplets = set()
    for a in range(low,high-1):
        for b in range(a,high):
            for c in range(b,high+1):
                if a**2 + b**2 == c**2:
                    triplets.add((a, b, c))
    return triplets


def relatively_prime(a, b):
    for i in range(2,int(math.sqrt(max(a,b)))+1):
        if a % i == 0 and b % i == 0:
            return False
    return True
