def on_square(x):
    return 2 ** (x-1)

def total_after(x):
    return sum(on_square(n) for n in range(1,x+1))
