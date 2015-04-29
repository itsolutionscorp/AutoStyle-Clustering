def on_square(x):
    n = 1
    for i in range(1, x):
        n = 2*n
    return n

def total_after(x):
    return sum([on_square(i) for i in range(1, x+1)])
