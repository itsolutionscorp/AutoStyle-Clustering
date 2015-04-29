def nth_prime(position):
    
    primes_list = [2, 3, 5, 7]
    c = 10
    while len(primes_list) <= position:
        if any(x == 0 for x in [c % i for i in range(2, 1+int(c**0.5))]):
            c += 1
        elif all(x != 0 for x in [c % i for i in range(2, 1+int(c**0.5))]):
            primes_list.append(c)
            c += 1
    return primes_list[position-1]
