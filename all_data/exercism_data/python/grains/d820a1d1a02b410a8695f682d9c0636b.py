def on_square(square):
    return 2 ** (square - 1)

def total_after(square):
    # from https://en.wikipedia.org/wiki/Geometric_progression
    # a(1-r^n)/(1-r)
    # where a = 1, r = 2, and n = square
    return (1 - (2 ** square)) / (1 - 2)
