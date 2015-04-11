def on_square(square):
    on_square = 1
    for i in range(1,square):
        on_square *= 2
    return on_square

def total_after(square):
    total = 1
    for i in range(1,square):
        total += on_square(i+1)
    return total
