def nth_prime(n):
    cur = 1
    primes = gen_primes(n*20)
    while cur < n:
        primes.next()
        cur += 1
    return primes.next()

def gen_primes(x):
    numbers = [0]*2 + [1]*x
    cur = 2
    while cur < x+1:
        yield cur
        for i in xrange(cur,x+1,cur):
            numbers[i] = 0
        cur = numbers.index(1,cur)
