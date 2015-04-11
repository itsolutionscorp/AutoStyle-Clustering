# William Morris
# exercism.io
# grains.py

# to optimize for speed, I will use geometric series instead of iteration
# for a + a*r + a*r**2 + a*r**3+...a*r**n-1 = a*(1-r**n)/(1-r) for the first
# n terms of a geometric series. http://en.wikipedia.org/wiki/Geometric_series

def on_square(board_square):
    # this is a simple power of 2 starting with 0 on square 1
    return 2**(board_square-1)

def total_after(board_square):
    # this is our geometric series with a=1 and r=2
    return (1-2**board_square)/(-1)

