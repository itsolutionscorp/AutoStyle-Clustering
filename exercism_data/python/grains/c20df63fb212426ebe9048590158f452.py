def on_square(n):
    """Returns the n item of the exponential series 2**(n-1)"""
    return 2**(n-1)

def total_after(n):
    """Returns the sum of the exponential series 2**(n-1) from 0 to n.
       Based on Geometric progression. Encyclopedia of Mathematics.
       URL: www.encyclopediaofmath.org/index.php?title=Geometric_progression
    """
    return 2**n-1
