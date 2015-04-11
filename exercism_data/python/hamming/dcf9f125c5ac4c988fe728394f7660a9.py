"""
calculates the hamming difference in two dna strands
"""
def distance(dna_strand_one, dna_strand_two):
    distance = 0
    for idx in xrange(len(dna_strand_one)):  # they are the same length
        if dna_strand_one[idx] != dna_strand_two[idx]:
            distance += 1

    return distance
