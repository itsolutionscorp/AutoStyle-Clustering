# hamming.py
# exercism: Python Exercise #5


def distance(str1, str2):
    """Return the # of char differences between two strings."""

    return map(lambda x, y: x == y, str1, str2).count(False)
