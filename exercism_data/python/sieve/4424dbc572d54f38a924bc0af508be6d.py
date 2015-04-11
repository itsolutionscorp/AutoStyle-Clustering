import math
  
#base = [2,3,5,7,11]

def sieve_func(n, base):
    prime_lookup = {i:'' for i in range(2,n+1)}
    base_nums = base
    multiplication_list =[]
    for num in base_nums:
        multiplication_list.extend([num * x for x in range(num, n+1)])
    for multiple in multiplication_list:
        prime_lookup[multiple] = 'not prime'
    primes =[k for k,v in prime_lookup.iteritems() if v == '' ]
    return primes
    

def sieve(n):
    b = [2,3,5,7,11]
    rt = int(math.ceil(math.sqrt(n)))
    # print rt
    if rt > b[-1]:
        b = sieve(rt)
        # print b
        return sieve_func(n,b)
    else:
        return sieve_func(n,b)
        
            
