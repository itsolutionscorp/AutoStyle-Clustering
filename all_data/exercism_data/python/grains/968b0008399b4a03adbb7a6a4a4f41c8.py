import functools


@functools.lru_cache(maxsize=64)
def on_square(n):
    if n == 1:
        return 1
    return 2 * on_square(n-1)


@functools.lru_cache(maxsize=64)
def total_after(n):
    if n == 1:
        return 1
    return on_square(n) + total_after(n-1)
