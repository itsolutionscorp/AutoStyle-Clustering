def primitive_triplets(b):
    """ Find all primitive pythagorean triplets having b as one of their
        components

        Args:
           b - an integer divisible by 4 (see explanantion below)"""
    if b % 4 != 0: raise ValueError("argument must be divisible by 4.")

    ans = set()
    for m in range(2,b/2+1):
        for n in range(0,m):
            if b == 2*m*n and (prime_factors(m).intersection(prime_factors(n)) == set([])):
                ans.add(((m**2 - n**2),
                         b,
                         (m**2 + n**2)
                         ))
    return ans

def triplets_in_range(low, high):
    """ Compute all pythagorean triplets (a,b,c) with min <= a,b,c <= max"""
    ans = set()
    for b in range(0,high+1):
        if b % 4 == 0:
            for item in primitive_triplets(b):
                i = 1
                while item[0]*i <= high and item[2]*i <= high:
                    if item[0]*i >= low and item[1]*i >= low and item[2]*i >= low:
                        ans.add((item[0]*i,
                                 item[1]*i,
                                 item[2]*i))
                    i += 1
    return ans

def is_triplet(d):
    return d[0]**2 + d[1]**2 == d[2]**2

def prime_factors(n):
    # Store our answer in this set
    ans = set()

    # The first possible prime factor is 2
    i = 2
    
    # Iterate until we are at the final case where
    # our divisor 1
    while i <= n:
        
        # If i goes into n
        if n % i == 0:
            # Modify n and store i
            n = n / i
            ans.add(i)
            
        # Otherwise, move on to the next integer
        # (We don't have to worry about integers that
        #  aren't prime being introduced here since
        #  lesser multiples will already be taken care of
        #  before:
        #
        #  i.e. 4 won't ever be in the answer because
        #  2 will have been added until exhaustion.
        else: i += 1
        
    return ans
