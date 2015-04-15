def sieve(limit):
    A = [True] * limit
    A[0] = A[1] = False
    for i in range(limit):
        if A[i]:
            for j in range(2*i,limit,i):
                A[j] = False
    return [x for x in range(limit) if A[x]]
