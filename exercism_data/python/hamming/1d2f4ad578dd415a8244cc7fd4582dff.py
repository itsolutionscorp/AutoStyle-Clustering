from itertools import zip_longest

def distance(dna1, dna2):
    return sum(nucleo1 != nucleo2 for nucleo1, nucleo2 in \
               zip_longest(dna1, dna2, fillvalue='-'))
