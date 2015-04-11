from functools import lru_cache

@lru_cache(maxsize=64)
def on_square(square):
    if not 0 < square < 65:
        raise ValueError('square should be between 1-64')

    return 2**(square-1)


def total_after(square):
    return sum(on_square(i) for i in range(1, square + 1))
