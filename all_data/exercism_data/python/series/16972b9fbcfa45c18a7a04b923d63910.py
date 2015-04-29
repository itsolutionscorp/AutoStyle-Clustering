'''
series.py

Get the list of consecutive number series
'''

def slices(string, n):
    '''
    Given a string of numbers, find the the list of consecutive number series
    of length n

    @param string: string of numbers
    @param n: length of series
    @return: list of all possibles series
    '''
    if n == 0 or len(string) < n:
        raise ValueError

    series = []
    for i in range(len(string) - n + 1):
        series.append([int(l) for l in string[i:i+n]])

    return series
