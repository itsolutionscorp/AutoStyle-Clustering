"""Calculate the number of grains of wheat on a chessboard.

The number of grains on each square doubles.
"""


def on_square(nth):
    """Return the number of grains on the n-th square."""
    # 1 << (n - 1) = 2 ** (n - 1).
    return 1 << (nth - 1)


def total_after(nth):
    """Return the total number of grains up to the n-th square."""
    # 1 + 2 + (2 ** 2) + ... + (2 ** (n - 1)) = (2 ** n) - 1 = (1 << n) - 1.
    return (1 << nth) - 1
