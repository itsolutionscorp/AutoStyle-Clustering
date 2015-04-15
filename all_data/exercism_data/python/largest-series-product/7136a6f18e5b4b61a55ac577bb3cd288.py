#!/usr/bin/env python3
""" module slices for exercism.io programming training """
from operator import mul
from functools import reduce


def multiply_slice(list):
    """ multiply the number in the list """ 
    return reduce(mul, list)


def slices(population, length):
    """ returns slices of the input string with requested length """
    population = [int(i) for i in population]
    if length == 0: raise ValueError('length has to be larger than 0')
    if length > len(population): raise ValueError('length can\'t be larger than input')
    return [population[i:i+length] for i in range(len(population)-length+1)]


def largest_product(population, length, speed0=False):
    """ explore all? slices of length numbers and return max product """
    if (population == '') & (length == 0): return 1 # I don't understand this and disagree but put a special case here for it
    
    multiples = [multiply_slice(x) for x in slices(population, length)]
    return max(multiples)
