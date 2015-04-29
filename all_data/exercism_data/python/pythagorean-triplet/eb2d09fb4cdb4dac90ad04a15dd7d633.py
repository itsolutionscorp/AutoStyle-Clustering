def triplets_in_range(mini, maxi):
    py_triplets = set()
    for c in range(mini, maxi+1):
        for b in range(mini, c):
            for a in range(mini, b):
                if a**2 + b**2 == c**2:
                    py_triplets.add((a, b, c))
    return py_triplets        
    
'''def primitive_triplets_in_range(mini, maxi):
    prim_triplets = set()
    for a, b, c in triplets_in_range(mini, maxi+1):
        if gcd(a, gcd(b, c)) == 1:
            prim_triplets.add((a, b, c))
    return prim_triplets'''
    
def primitive_triplets(b):
    prim_triplets = set()
    for (m, n) in b_solver(b):
        a = (m**2 - n**2)
        b = 2*m*n
        c = (m**2 + n**2)
        prim_triplets.add(tuple(sorted((a, b, c))))
    if prim_triplets == set():
        raise ValueError
    else:
        return prim_triplets
    
def b_solver(b):
    for m in range(b):
        for n in range(b):
            if m>n and (m-n)%2 and 2*m*n == b and gcd(m, n) == 1:
                yield (m, n) 
                  
def gcd(x, y):
    while y:
        x, y = y, x % y
    return x 
                
def is_triplet((a, b, c)):
    a, b, c = sorted((a, b, c))
    return a**2 + b**2 == c**2
