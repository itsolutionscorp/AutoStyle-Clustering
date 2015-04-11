# William Morris
# exercism.io
# pythgorean_triplet.py

import math

# a^2 + b^2 = c^2
# a=(m^2-n^2)
# b = 2*m*n
# c = (m^2 + n^2)
# a and c are odd

def primitive_triplets(b):
    # b must be divisible by 4 or ValueError
    # a>b or b>a
    if b%4:
        raise ValueError('b must be divisible by 4')
    
    
    # find all possible pairs (m,n) such that
    # m>n
    # m-n is odd
    # b = 2*m*n
    # m and n are coprime

    # find all whole factors of b/2, set to m
    # find all co- factors pairs, set to m,n
    # eliminate all pairs that fail criteria above
    mset = whole_factors(b/2)
    
    triplets = set()
    for (m,n) in mset:
        a = (m**2 - n**2)
        b = 2*m*n
        c = (m**2 + n**2)
        triples = [a,b,c]
        triples.sort()
        triplets.add(tuple(triples))
    return triplets
    

def triplets_in_range(triple_min, triple_max):
    #find b_min so smallest multiple of 4>=range_min
    b_min = 4 #triple_min
    #while b_min%4:
    #    b_min += 1
    #find b_max as largest multipe of 4<= sqrt(triple_max^2 - triple_min^2)
    b_max = triple_max #int(math.floor(math.sqrt(triple_max**2 - triple_min**2)))
    while b_max%4:
        b_max -= 1
    
    #find all triples for all b (multiples of 4 in range of b) (primitive_triplets)
    triplets = set()
    for b in range(b_min,b_max+4,4):
        triplets = triplets.union(primitive_triplets(b))        
    #remove triplets for which a or c fall outside of range,
    #order for a,b,and c has been lost by sorting
    for triplet in triplets.copy():
        (t1,t2,t3) = triplet
    #need to insert non-primitive triplets in range
        k = 2
        while t3*k <= triple_max:
            triplets.add((t1*k,t2*k,t3*k))
            k += 1
    #remove triplets for which a or c fall outside of range,
    #order for a,b,and c has been lost by sorting
    for triplet in triplets.copy():
        (t1,t2,t3) = triplet
        if t1 < triple_min or t3 > triple_max:
            triplets.remove(triplet)
    return triplets
            
    

def is_triplet((x,y,z)):
    # find which component is c, then b and a
    b = 0  #avoids errors if no value meets criteria for b
    for element in (x,y,z):
        if element == max(x,y,z):
            c = element
        elif not element%4:
            b = element
        else:
            a = element
    # check a^2 + b^2 = c^2
    # check for coprime-ness
    return is_coprime(a,b) and is_coprime(a,c) and is_coprime(b,c) and c == math.sqrt(a**2+b**2)
    

def prime_factors(number):
    candidate = 2
    factors = set()
    while number > 1:
        while not number%candidate:
            factors.add(candidate)
            number /= candidate
        candidate += 1   
    return factors

def whole_factors(b):
    pairs = set()
    for m in range(1,b+1):
        if not b%m:
            n = b/m
            if m>n and (m-n)%2 and is_coprime(m,n):
                pairs.add((m,n))
    return pairs
    
    

def is_coprime(m,n):
    mset = prime_factors(m)
    nset = prime_factors(n)
    return mset.isdisjoint(nset)
