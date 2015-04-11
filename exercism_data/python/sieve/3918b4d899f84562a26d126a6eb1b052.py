'''
Created on Sep 26, 2014

@author: adsmith
'''
def sieve(max_num):
    numbers = {number:True for number in range(3, max_num+1)}
    numbers[2] = True
    for prime in numbers:
        if not numbers[prime]:
            continue # number is not prime
        for multiple in range(prime*2, max_num+1, prime):
            numbers[multiple] = False
    return [key for key,value in numbers.items() if value]
