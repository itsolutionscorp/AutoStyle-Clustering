import math,cProfile
def nth_prime(number):
        primes = []
        i=1
        while True:
            i+=1
            if i%2==1 or i==2:
                prime = True
                if primes:
                        if len(primes) == number:
                                return primes[len(primes)-1]
                        i_root = math.sqrt(i)
                        for j in primes:
                                if j>i_root and i>3:
                                        break
                                if i%j==0:
                                    prime = False
                                    break
                        if i not in primes and prime:
                                primes.append(i)
                else:
                        primes.append(i)
            
        
