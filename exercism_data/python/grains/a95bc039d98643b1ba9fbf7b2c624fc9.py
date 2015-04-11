def on_square(square):
    grains = 2**(square - 1)
    return grains

def total_after(square):
    total = 0
    for n in range(1, square + 1):
        total += on_square(n)
    return total
