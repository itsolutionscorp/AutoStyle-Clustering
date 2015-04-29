from itertools import count    

def prime_factors(number):
    result = []
    for n in count(2):
        while number % n == 0:
            result.append(n)
            number /= n
        if number == 1:
            break
    return result
