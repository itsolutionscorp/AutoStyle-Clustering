"""
The file series.py contains functions that manipulate a series of numbers

Made for Exercism.io
"""


def slices(series, n):
    """
    The function slices takes in a string of digits and returns all the
    possible consecutive number series of length 'n' in the string
    """
    if (n > len(series)):
        raise ValueError
    elif (n == 0):
        raise ValueError
    
    finalList = []
    count = 0
    
    while ((count + n - 1) < len(series)):
        sliceList = []
        for x in xrange(0, n):
            sliceList.append(int(series[count + x]))
        finalList.append(series)
        count += 1
    
    return finalList

