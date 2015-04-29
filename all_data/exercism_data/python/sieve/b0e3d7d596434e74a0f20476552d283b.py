from itertools import zip_longest

def sieve(limit):
    '''Sieve of Eratosthenes'''
    a = dict(zip_longest(range(2,limit),[],fillvalue=True))
    for n in range(2,len(a)):
        if a[n]:
            p = n**2
            while p < limit:
                a[p] = False
                p += n
    return([n for n in a.keys() if a[n]])
