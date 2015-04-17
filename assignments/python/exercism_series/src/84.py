def slices(digits, n):
    if n > len(digits):
        raise ValueError("Your series is too short!")
    elif n == 0:
        raise ValueError("That slice is too small!")
    return [map(int, digits[first : first+n]) 
            for first in xrange(0, len(digits)-n+1)]
