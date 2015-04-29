def slices(num, length):
    if length > len(num):
        raise ValueError("Slice %s exceeds %s number length" % (length, num))
    return [map(int, list(num[n:n+length]))  for n in xrange(len(num) - length + 1)]

def largest_product(num, snip):
    if snip == 0:
        return 1
    products = [ reduce(lambda x,y: x * y, s) for s in slices(num, snip) ]
    return max(products)
