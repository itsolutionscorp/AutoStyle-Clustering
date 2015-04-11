import functools

def on_square(n):
    return 2 ** (n - 1)

def total_after(n):
    return sum(map(on_square, range(1, n + 1)))
