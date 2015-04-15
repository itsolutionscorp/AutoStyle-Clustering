from bisect import bisect_left

def binary_search(a, x, lo=0, hi=None):   # can't use a to specify default for hi
    hi = hi if hi is not None else len(a) # hi defaults to len(a)   
    pos = bisect_left(a,x,lo,hi)          # find insertion position
    return (pos if pos != hi and a[pos] == x else -1)

def sieve(n):
    prime_candidates = range(2, n)
    curr_prime_index = 0
    while(curr_prime_index < len(prime_candidates)):
        curr_prime = prime_candidates[curr_prime_index]
        curr_prime_multiple = 2*curr_prime
        while(curr_prime_multiple < n):
            index = binary_search(prime_candidates, curr_prime_multiple)    
            if index != -1:
                prime_candidates.pop(index)
            curr_prime_multiple += curr_prime
        curr_prime_index += 1
    return prime_candidates
