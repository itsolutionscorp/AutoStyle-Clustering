__author__ = 'samuelbarthelemy'


def distance(dna, rna):

    count = 0
    distance_amount = 0

    while count < dna.__len__():
        if dna[count] != rna[count]:
            distance_amount += 1
        count += 1
    return distance_amount
