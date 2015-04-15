def sieve(upper_range):
    not_prime = []
    prime=[]
    for n in range(2,upper_range):
        if n in not_prime:
            if prime == []:
                continue
            else:
                for m in prime:
                    if m*n>upper_range:
                        break
                    not_prime.append(m*n)
                    continue
        else:
            prime.append(n)
            for m in prime:
                if m*n>upper_range:
                    break
                not_prime.append(m*n)
    return prime
                    
        
