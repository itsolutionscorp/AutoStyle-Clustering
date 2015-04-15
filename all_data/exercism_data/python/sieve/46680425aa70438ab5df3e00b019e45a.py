from math import ceil
from math import sqrt


def sieve(number):
    numbers = range(2,number+1)
    for x in range(2,int(ceil(sqrt(number)))):
        y = x
        while (x*y < number+1):
            try:
                numbers.pop(numbers.index(x*y))
            except:
                pass
            y = y + 1
    return numbers
