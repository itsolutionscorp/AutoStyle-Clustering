#
#William Morris
#exercism.io
#prime_factors.py

def prime_factors(number):
    divisor = 2
    divisors = []
    while number > 1:
        while not number%divisor:
            divisors.append(divisor)
            number /= divisor
        divisor += 1   
    return divisors
            
