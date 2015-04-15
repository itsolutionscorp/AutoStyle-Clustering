# Get hamming distance = number of exchanged characters.
# Only for strings of the same length.


def distance(string1, string2):

    if len(string1) != len(string2):
        raise ValueError('Strings must be of equal length.')

    count = sum(1 for c1, c2 in zip(string1, string2) if c1 != c2)

    return count
