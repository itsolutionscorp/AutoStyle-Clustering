__author__ = 'grdunn'


def hamming(n1, n2):
    """

    :type n2: str
    :type n1: str
    :param n1:
    :param n2:

    """
    hamnum = 0

    for i in range(0, min(len(n1), len(n2))):
        if n1[i] != n2[i]:
            hamnum += 1
    hamnum += abs(len(n1)-len(n2))
    return hamnum
