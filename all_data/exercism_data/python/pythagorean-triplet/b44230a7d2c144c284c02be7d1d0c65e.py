import math

def primitive_triplets(b):
    if not b % 4 == 0:
        raise ValueError('Argument must be divisible by 4.')
    potential_primitives = set()
    primitives = set()

    #create set of potential primitives
    for n in range(1, int(math.ceil(math.sqrt(b / 2)))):
        m = (b / 2.0) / n
        if m % 1 == 0:
            a = int(m)**2 - n**2
            c = int(m)**2 + n**2
            potential_primitives.add(tuple(sorted([a,b,c])))

    #coprime?
    for triplet in potential_primitives:
        invalid_triplet = False
        factors = []
        for number in triplet:
            for i in range(2, int(math.ceil(number/2))):
                if (number / float(i)) % 1 == 0:
                    if not i in factors:
                        factors.append(i)
                    else:
                        invalid_triplet = True
        if not invalid_triplet:
            primitives.add(triplet)
    return(primitives)
    

def triplets_in_range(minimum,maximum):
    #Gets list of all primitive triples UP to maximum a/b/c value
    b_list = [x for x in range(maximum+1) if x % 4 == 0]
    primitives = set()
    for b in b_list:
        for a_b_c in primitive_triplets(b):
            primitives.add(a_b_c)
    triples = set()

    #tests multiplier integers against primitive triplets until c exceeds maximum range
    for a,b,c in primitives:
        k = 0
        while True:
            k += 1
            if (minimum <= (a*k) and minimum <= (b*k)):
                if (c*k) <= maximum:
                    triples.add((a*k,b*k,c*k))
                else:
                    break
    return triples
        

def is_triplet(num):
    #placing 'c' at end of list with sort
    n = sorted(num)
    return n[0]**2 + n[1]**2 == n[2]**2

