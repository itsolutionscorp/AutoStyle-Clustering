from functools import reduce
from operator import mul


def largest_product(digits, length):
    """
    Get the largest product of all series with a given length in a series of digits.

    :param str digits:
    :param int length:
    :return: int
    """
    if length > len(digits):
        raise ValueError('Invalid length.')

    return max(reduce(mul, s) for s in slices(digits, length)) if length >= 1 else 1


# This is mainly copy'n'pasted from the former series exercise.
# I tried to import that module, but couldn't get it work.
# Any ideas on that?
def slices(digits, length):
    """
    Slices a series of digits.

    :param str digits: A series of digits
    :param int length: The length of the requested slices
    :return: [[int]]
    :raise: ValueError
    """
    if 0 <= length > len(digits):
        raise ValueError('Invalid length.')

    return list(_get_slices(digits, length))


def _get_slices(digits, length):
    """
    Get slices with given length from a series of digits.

    :param str digits:
    :param int length:
    :return: collections.Iterable[int]
    """
    return (_to_list(digits[i:i + length]) for i in range(len(digits) - length + 1))


def _to_list(series):
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
