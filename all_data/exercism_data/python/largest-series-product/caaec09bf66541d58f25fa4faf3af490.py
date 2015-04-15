import operator

def slices(s, n):
    if n == 0 or n > len(s):
        raise ValueError
    return [map(int,s[i:i+n]) for i in range(len(s)) if i+n <= len(s)]

def largest_product(s, n):
    if not s and not n:
        return 1
    return max(map(lambda x: reduce(operator.mul,x), slices(s,n)))
