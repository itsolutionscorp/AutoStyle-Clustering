def sieve(bound):
    bound += 1
    numbers = [number for number in range(2, bound)]
    primes = []
    while numbers:
        new_prime = numbers.pop(0)
        primes.append(new_prime)
        multiple = new_prime + new_prime
        while  multiple <= bound:
            try:
                numbers.remove(multiple)
            except ValueError:
                pass
            finally:
                multiple += new_prime
                
    return primes
