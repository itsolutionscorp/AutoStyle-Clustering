"""
series.py: program that will take a string of digits and give you
all the possible consecutive number series of length `n` in that string.
"""
def slices(series, number):
    """
    Returns a list of lists containing possible consecutive number series
    of a series with length determined by number.
        series: a string containing a consecutive number series
        number: the length of the possible series combinations
    """
    if number == 0 or number > len(series):
        raise ValueError
    digits = [int(i) for i in series]
    return [digits[i:i + number] for i in range(len(digits) - number + 1)]
