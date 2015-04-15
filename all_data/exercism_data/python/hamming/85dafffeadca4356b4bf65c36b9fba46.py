__author__ = 'Cedric Zhuang'


def distance(x, y):
    return len(filter(lambda i: i[0] != i[1], zip(x, y)))
