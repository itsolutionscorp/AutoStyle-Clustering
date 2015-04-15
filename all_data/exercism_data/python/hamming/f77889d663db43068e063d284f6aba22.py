
def distance(a,b):
    # inspect each letter in a to ensure that it matches b, sum the ones that don't
    return sum([a[i]!=b[i]for i in xrange(len(a))])
