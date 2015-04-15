# William Morris
# exercism.io
# sieve.py
           
def sieve(limit):
    marked_list = []
    primes = []
    prime = 2
    while prime <= limit:
        primes += [prime]
        #find all multiples of prime and mark on list
        multiplier = prime
        multiple = prime * prime
        while multiple <= limit:
            multiple = prime * multiplier
            marked_list += [multiple]
            multiplier += 1
        #find next prime, which will be the next number not on the marked list
        old_prime = prime
        next_number = prime +1
        while old_prime == prime:
            if next_number not in marked_list:
                prime = next_number
            next_number += 1
        
    return primes     

