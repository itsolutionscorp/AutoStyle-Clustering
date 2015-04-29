def slices(series, size):
    '''
    generates lists of the given size containing
    the consecutive digits in series
    '''

    if size > len(series):
        raise ValueError(
            'Size of the slices cannot be bigger than ' + \
            'the length of the numeric series')
    elif size < 1:
        raise ValueError(
            'Size of the slices must be at least 1'

    return _get_slices(_get_digits(series), size)


def _get_digits(series):
    return [int(digit) for digit in series]


def _get_slices(digits, size):
    return [digits[i:i+size] for i in xrange(len(digits) + 1 - size)]
