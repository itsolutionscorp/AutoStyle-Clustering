def on_square(n):
    return 2**(n-1)


def total_after(n):
    return sum(on_square(i+1) for i in range(n))
