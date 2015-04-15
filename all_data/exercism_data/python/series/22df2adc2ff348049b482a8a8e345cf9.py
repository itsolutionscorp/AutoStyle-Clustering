__author__ = 'Cedric Zhuang'


def slices(s, length):
    slices_len = len(s) - length + 1
    if slices_len < 1 or length <= 0:
        raise ValueError("error parameter %s" % length)

    s = map(int, s)
    return [s[i:i+length] for i in range(slices_len)]
