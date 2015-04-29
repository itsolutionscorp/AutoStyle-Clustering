def prime_factors(num):
    factors = []
    prime = 2
    
    while num>1:
        if not num%prime:
            factors.append(prime)
            num /= prime
        else:
            if prime<>2:
                prime += 2
            else:
                prime += 1
    
    return factors
