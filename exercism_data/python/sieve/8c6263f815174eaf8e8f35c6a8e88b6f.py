
def sieve(nbr):
    a=range(2,nbr)
    b=range(2,nbr)
    [a.remove(i*j) for i in a for j in b if i*j in a]
    return a
