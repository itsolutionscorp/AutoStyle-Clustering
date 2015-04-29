# William Morris
# exercism.io
# sieve.py
           
def sieve(limit):
    numbers = range(3,limit+1,2)
    for prime in numbers:
        for number in numbers:
            if prime*number >= limit:
                break
            else:
                numbers.remove(prime*number)
    return numbers
