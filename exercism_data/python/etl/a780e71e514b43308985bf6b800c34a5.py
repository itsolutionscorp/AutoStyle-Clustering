from types import StringType

__author__ = 'jeffmarkey'

def transform(old):
    new = {}
    for element in old.keys():
        for char in old[element]:
            new[char.lower()] = element
    return new
