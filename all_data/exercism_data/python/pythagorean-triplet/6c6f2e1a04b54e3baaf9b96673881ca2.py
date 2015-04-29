def primitive_triplets(num):
    #a=(m^2-n^2), b=2*m*n and c=(m^2+n^2)
    #m>n, m-n is odd, b=2*m*n and m and n are coprime.
    if num % 2 == 1:
        raise ValueError
    pairs = []
    results = []
    for n in range(1, num):
        for m in range(n, num):
            if n*m == num/2:
                pairs.append((n,m))

    for pair in pairs:
        if pair[1] > pair[0]:
            if (pair[1] - pair[0]) % 2 == 1:
                if iscoPrime(pair[1],pair[0]):
                    a = int(pair[1]**2 - pair[0]**2)
                    b = 2*pair[1] * pair[0] # This is == num
                    c = int(pair[1]**2 + pair[0]**2)
                    if b > a:
                        results.append((a,b,c))
                    else:
                        results.append((b,a,c))
    return set(results)

def triplets_in_range(lowerbound, upperbound):
    results = []
    for x in xrange(lowerbound, upperbound+1):
        y,z = x+1, x+2
        while z <= upperbound+1:
            while z*z <= (x*x + y*y):
                if is_triplet((x,y,z)) and z <= upperbound:
                    results.append((x,y,z))
                z += 1
            y += 1
    return set(results)

def is_triplet(test_set):
    vals = sorted(test_set)
    return (vals[0]**2 + vals[1]**2 == vals[2]**2)

def gcd (m, n):
    while (m != n):
        if (m > n):
            m = m - n
        else:
            n = n - m
    return m

def iscoPrime(a, b):
    if (gcd(a, b) != 1):
      return False
    else:
      return True
