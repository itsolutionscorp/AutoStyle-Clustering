from math import pow, trunc, floor

def on_square(square):
    return trunc(pow(2, square-1))

def total_after(squares):
    grains = 0.0
    for square in range(0,squares):
        grains += pow(2, square)
    return trunc(grains)
