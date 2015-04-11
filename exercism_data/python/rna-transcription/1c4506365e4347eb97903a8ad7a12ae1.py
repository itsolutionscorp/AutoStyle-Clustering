# dna.py

def to_rna(dna):
    """
        take a string dna and returns
        its rna complement.
    """
    if len(dna) < 2:
        if dna.lower() == 'g':
            return 'C'
        elif dna.lower() == 'c':
            return 'G'
        elif dna.lower() == 't':
            return 'A'
        elif dna.lower() == 'a':
            return 'U'
    else:
        return to_rna(dna[:1]) + to_rna(dna[1:])

