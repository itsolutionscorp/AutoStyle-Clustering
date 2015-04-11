def _get_slice(series, start, length):
    """Slice string `series` from index `start` for `length` characters 
    and convert the resulting string into a list of ints
    """
    return [ int(s) for s in series[start:start+length] ]


def slices(series, length):
    """Get all substrings of length `length` from string `series`.
    Each substring is list of ints, rather than a string
    """
    if length == 0:
        raise ValueError("Slice length may not be 0")
    num_slices = len(series)-length+1
    if num_slices <= 0:
        raise ValueError("Slice length may not be longer than series")
    return [ _get_slice(series, i, length) for i in range(0, num_slices) ]
