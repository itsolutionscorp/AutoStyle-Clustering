def sieve(upper_range):
    not_prime = [1]
    prime=[]
    for n in range(upper_range):
        if n+1 in not_prime:
            continue
        else:
            prime.append(n+1)
            for m in range((upper_range/(n+1))+1):
                k=(m+1)*(n+1)
                if not (k in not_prime):
                    not_prime.append(k)
    return prime
                    
        
