"""
Series
"""
__version__ = "0.0.1"
__all__ = ['__version__', 'slices']
def slices(digits, num):
    """Take a string of digits and return all possible consecutive 
    number series of length `num` in that string.
    """
    to_int = lambda str_slice : map(int, list(str_slice))
    if 0 < num <= len(digits):
        return [to_int(digits[i:i+num]) for i in xrange(len(digits)-num+1)]
    raise ValueError("bad slice length")
