def distance(strand1, strand2):
    return len([nucleotide for index, nucleotide in enumerate(strand1) if strand2[index] != nucleotide])
