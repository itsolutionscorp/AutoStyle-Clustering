#!/usr/bin/env python3
""" module slices for exercism.io programming training"""


def slices(population, length):
    """ returns slices of the input string with requested length """
    population = [int(i) for i in population]
    if length == 0: raise ValueError('length has to be larger than 0')
    if length > len(population): raise ValueError('length can\'t be larger than input')
    return [population[i:i+length] for i in range(len(population)-length+1)]
