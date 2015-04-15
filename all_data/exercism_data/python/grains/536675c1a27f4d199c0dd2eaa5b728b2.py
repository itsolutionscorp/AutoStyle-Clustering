def on_square(n):
    """Return number of grains on square n."""
    return 2 ** (n - 1)


def total_after(n):
    """Return total number of grains up to square n."""
    return sum([on_square(i) for i in range(1, n + 1)])
