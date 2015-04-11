# This version implements caching of results in the most
# efficient way that I know of, minimizing the computations
# within the two look up functions and prefilling the
# cache on module load to not have to try for the cache
# value being available or not.
#
# This implementation has proven to outperform the code with
# only computations in the best way. This implementation is
# 30% faster than the implementation that only uses computations
# (querying every square of the chessboard 10,000 times)

on_square_cache = [ 0 ]
on_square_cache.extend( 1 << (square - 1) for square in range (1, 65) )

def on_square(square):
    return on_square_cache[square]

total_after_cache = [ 0 ]
total_after_cache.extend((1 << square) - 1 for square in range(1, 65))

def total_after(square):
    return total_after_cache[square]
