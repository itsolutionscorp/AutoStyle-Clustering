def sieve(n):
    eratosthenes = list(range(2,n+1))
    for i in eratosthenes:
        for j in eratosthenes:
            if j > i and j % i == 0:
                eratosthenes.remove(j)
    return eratosthenes

if __name__ == '__main__':
    a = sieve(10)
    print(a)
