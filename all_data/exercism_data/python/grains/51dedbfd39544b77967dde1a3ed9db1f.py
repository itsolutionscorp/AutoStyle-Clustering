# This version implements a fun way of caching the outcomes
# of the square computations. The idea for the approach was
# to compute the values for a square only once and use the
# cached values after this.
#
# This cache implementation does not outperform the code 
# with only computations though. The implementation that
# only uses computations is 30% faster (querying every square
# of the chessboard 10,000 times)

from collections import defaultdict

class Square(object):
    def __init__(self, square):
        print "Init " + repr(square) + "\n"
        self.on_square = 1 << (square - 1)
        self.total_after = (1 << square) - 1

class Squares(defaultdict):
    def __missing__(self, square):
        self[square] = Square(square)
        return self[square]

squares = Squares()

def on_square(square):
    return squares[square].on_square

def total_after(square):
    return squares[square].total_after
