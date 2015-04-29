def sieve(n):
    cands = []
    # Generate a list of lists of format [x,0], where x is all the numbers up to n inclusive.
    for x in range(2,n+1):
        cands.append([x,0])
        
    # Iterate through cands, applying sieve.
    for i in range(len(cands)):
        # If cands[i] is marked as not prime, we can skip it.
        if cands[i][1] != 0:
            continue
        # Otherwise, it is prime and we should mark all numbers divisible by it as not prime.
        for j in range(i+1, len(cands)):
            if cands[j][0]%cands[i][0]==0:
                cands[j][1] = 1
    # Collate and return all unmarked numbers, as they are prime.
    primes = []
    for cand in cands:
        if cand[1] == 0:
            primes.append(cand[0])
    return primes
