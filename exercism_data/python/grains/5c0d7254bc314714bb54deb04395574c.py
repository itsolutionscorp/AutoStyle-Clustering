def on_square(squarenum):
    return 1 << squarenum - 1
    
def total_after(squarenum):
    return sum([on_square(x) for x in xrange(1, squarenum + 1)])
