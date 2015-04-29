def on_square(sq):
    """Compute the number of grains on a chess square"""
    return 2**(sq - 1)

def total_after(sq):
    """Compute the total of grains on the chess board up to given square"""
    return 2**sq - 1
