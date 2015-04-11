# William Morris
# exercism.io
# sieve.py
           
def sieve(limit):
    numbers = range(3,limit+1,2)
    top_prime = int(round(limit**0.5))
    for prime in numbers:
        if prime > top_prime:
            break
        for number in numbers[numbers.index(prime):]:
            if prime*number >= limit:
                break
            else:
                numbers.remove(prime*number)
    return [2]+numbers

