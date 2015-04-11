def sieveHelper(ls):
    if not ls:
        return []
    else:
        return [ls[0]] + sieveHelper([x for x in ls[1:] if x % ls[0]])

def sieve(n):
    return sieveHelper(range(2, n+1))
