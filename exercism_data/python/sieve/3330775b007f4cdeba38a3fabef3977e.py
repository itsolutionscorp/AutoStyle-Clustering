from  math import ceil
from math import sqrt

def sieve(number):
    nonprimes = []
    primes = []
    for x in range(2,int(ceil(sqrt(number)))):
        y = x
        result = 0
        while (result < number +1):
            result = x*y
            nonprimes.append(result)
            y = y + 1

    for a in range(2,number):
        if a not in nonprimes:
            primes.append(a)
    return primes
  
#def main():
#    print sieve(5000)
#
#
#
#if __name__ == "__main__":
#    main()
