__author__ = 'Cedric Zhuang'


def distance(x, y):
    diff = 0
    for i, _ in enumerate(x):
        if x[i] != y[i]:
            diff += 1
    return diff
