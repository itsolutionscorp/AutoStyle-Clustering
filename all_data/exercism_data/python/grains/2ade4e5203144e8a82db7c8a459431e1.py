def on_square(square):
    return 2 ** (square - 1)

def total_after(square):
    on_squares = [ on_square(square) for square in range(1, square + 1) ]
    return sum(on_squares)
