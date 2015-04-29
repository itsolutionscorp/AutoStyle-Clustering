__author__ = 'Flavio Miranda'


def sum_of_multiples(number, lst=[3, 5]):
    return sum({i: 0 for i in range(number) for y in lst if y != 0 and i % y == 0}.keys())
