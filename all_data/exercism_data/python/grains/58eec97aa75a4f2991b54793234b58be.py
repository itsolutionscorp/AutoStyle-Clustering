# This version implements caching of results in the most
# efficient way that I know of.
#
# This cache implementation outperform the code with only
# computations. This implementation is 12% faster than the
# implementation that only uses computations (querying every
# square of the chessboard 10,000 times)
#
# Note however that the "warming up" of the caches costs
# some overhead. When I update the code to always run
# into a cache miss, the code runs 60% slower than an
# computation-only version. Not that it is a big issue,
# since warming up the full cache takes less than a
# millisecond, so in my opinion having the cache around
# is a good optimization here, especially when one expects
# a lot of repeated look up calls.

on_square_cache = [0] * 65

def on_square(square):
    if not on_square_cache[square]:
        on_square_cache[square] = 1 << (square - 1)
    return on_square_cache[square]

total_after_cache = [0] * 65

def total_after(square):
    if not total_after_cache[square]:
        total_after_cache[square] = (1 << square) - 1
    return total_after_cache[square]
