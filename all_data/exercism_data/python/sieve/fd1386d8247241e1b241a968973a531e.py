from array import array

def sieve(n):
    arr = array('B', [0]*n)
    # 0 and 1 are not primes
    arr[0] = 1
    arr[1] = 1
    res = []
    for i in range(2, n):
        if not arr[i]: # i^th element of sieve is unmarked
            res.append(i)
            for j in range(2*i,n,i):
                arr[j] = 1
    return res
