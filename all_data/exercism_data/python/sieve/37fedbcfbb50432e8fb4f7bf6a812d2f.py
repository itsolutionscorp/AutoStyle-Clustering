#
#
#

def sieve(n):
    master = [True]*n
    i = 2
    while i < n**0.5:
        if master[i]:
            j = i**2
            while j < n:
                master[j] = False
                j = j + i
        i = i + 1
    return [i for i in range(0,n) if master[i] and i > 1]
