def slices(digits, n):
    if n > len(digits):
        raise ValueError("Your series is too short!")
    elif n == 0:
        raise ValueError("That slice is too small!")
    slices = []
    last = 0
    for first in xrange(0,len(digits)-n+1):
        last = first + n
        slice_ = list(map(int,digits[first:last]))
        slices.append(slice_)
    return slices
