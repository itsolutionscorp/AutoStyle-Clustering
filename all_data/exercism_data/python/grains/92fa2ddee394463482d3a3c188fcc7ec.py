def on_square(square):
    return 2**(square-1)

def total_after(square):
    return sum([on_square(s) for s in range(1,square+1)])
