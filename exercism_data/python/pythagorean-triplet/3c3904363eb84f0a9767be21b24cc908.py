#finds all pythagorean triplets within an input range
def triplets_in_range(mini, maxi):
    py_triplets = set()
    for c in range(mini, maxi+1):
        for b in range(mini, c):
            for a in range(mini, b):
                if a**2 + b**2 == c**2:
                    py_triplets.add((a, b, c))
    return py_triplets        

#finds primitve triplets that include a given number 'b'.
#Components of b are found using b_solver function   
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

#uses Euclid's formula to derive elements m, n of b satisfying given conditions.
#uses gcd function to determine if m, n are coprime    
def b_solver(b):
    for m in range(b):
        for n in range(b):
            if m>n and (m-n)%2 and 2*m*n == b and gcd(m, n) == 1:
                yield (m, n) 

#finds greatest common divisor among two numbers                  
def gcd(x, y):
    while y:
        x, y = y, x % y
    return x 

#determines if given tuple is a pythagorean triplet                
def is_triplet((a, b, c)):
    a, b, c = sorted((a, b, c))
    return a**2 + b**2 == c**2
