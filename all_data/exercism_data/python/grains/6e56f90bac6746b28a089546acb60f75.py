on_squares = [2 ** n for n in range(64)]


def on_square(n):
    return on_squares[n-1]


def total_after(n):
    return sum(on_squares[:n])
