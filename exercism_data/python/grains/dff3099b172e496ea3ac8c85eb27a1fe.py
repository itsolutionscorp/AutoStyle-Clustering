def on_square(square):
    return 2 ** (square-1)

def total_after(square):
    return sum(on_square(x) for x in xrange(1,square+1))
