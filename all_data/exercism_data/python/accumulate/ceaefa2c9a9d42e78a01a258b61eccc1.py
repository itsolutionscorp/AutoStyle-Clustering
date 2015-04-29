__author__ = 'nebur1989'


def accumulate(collection, function):
    result = []
    for element in collection:
        result.append(function(element))
    return result
