def to_rna(dna_strand):
    return dna_strand.replace('A', 'U')\
                     .replace('T', 'A')\
                     .replace('C', 'X')\
                     .replace('G', 'C')\
                     .replace('X', 'G')\
