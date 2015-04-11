def compare_strands(x, y):
    """
    Compare elements from a DNA strand.

    :param x: Element from strand 1
    :param y: Element from strand 2
    :return: 1 if the elements are not equal else 0.
    """
    return 1 if x != y else 0


def distance(strand1, strand2):
    strand_length = len(strand1)

    return sum([compare_strands(strand1[index], strand2[index]) for index in range(strand_length)])
