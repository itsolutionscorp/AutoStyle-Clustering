__author__ = 'Norm'

def accumulate(l, func):
    result = []
    for item in l:
        result.append(func(item))
    return result
