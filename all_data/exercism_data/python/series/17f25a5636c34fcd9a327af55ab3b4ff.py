""" module doc
"""


def slices(series, length):
    """ slices series in length long lists
    """
    # boundaries check
    if length < 1 or len(series) < length:
        raise ValueError

    series = [int(i) for i in series]
    return [series[i:i+length] for i in range(len(series)+1-length)]
