def slices(digits, length):
    """
    Slices a series of digits.

    :param str digits: A series of digits
    :param int length: The length of the requested slices
    :return: [[int]]
    :raise: ValueError
    """
    if length == 0 or length > len(digits):
        raise ValueError('Invalid length.')

    return list(get_slices(digits, length))


def get_slices(digits, length):
    """
    Get slices with given length from a series of digits.

    :param str digits:
    :param int length:
    :return: collections.Iterable[int]
    """
    i = 0
    while i + length <= len(digits):
        yield to_list(digits[i:i + length])
        i += 1


def to_list(series):
    """
    Splits a series to a list of single digits.

    :param str series:
    :return: [int]
    :raise: ValueError
    """
    try:
        return list(map(int, list(series)))
    except:
        raise ValueError('Series can only contain digits.')
