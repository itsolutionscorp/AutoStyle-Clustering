import math,cProfile
def nth_prime(number):
        primes = [2]
        i=1
        index=1
        while index<number:
                i+=2
                prime = True
                i_root = math.sqrt(i)
                for j in (primes):
                        if i%j==0:
                            prime = False
                            break
                        if j>i_root:
                                break
                if prime:
                        primes.append(i)
                        index+=1
        return primes[-1]
