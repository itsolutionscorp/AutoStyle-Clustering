def on_square(num):
    return 2**(num-1)
def total_after(max):
    if max == 0:
        return 0
    else:
        return (2**(max-1)) + total_after(max-1)
