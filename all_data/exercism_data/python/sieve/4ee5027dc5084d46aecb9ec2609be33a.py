def sieve(n):
    # use integer as a bit array, a 1 bit means not prime
    c = 3
    max = 1 << n  # maximum bit position
    p = 2         # initial prime
    bit = 1 << p  # bit position for p
    while p <= n:
        m = bit << p  # m is bit for a multiple of p
        while m <= max:
            c |= m    # mark each multiple as a composite
            m <<= p
        p += 1        # scan forward from p to next prime
        bit <<= 1
        while bit & c:
            p += 1
            bit <<= 1
    l = []            # build result list
    for p in range(n+1):
        if not c & 1:
            l.append(p)
        c >>= 1
    return l
