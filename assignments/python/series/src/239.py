def slices(number, n):
    if n == 0 or n > len(number):
        raise ValueError("YOU CAN'T DO THAT!!!!!!")
    r = map(int, number)
    a = [r[i:i+n] for i in xrange(len(r)-(n-1))]
    return a
