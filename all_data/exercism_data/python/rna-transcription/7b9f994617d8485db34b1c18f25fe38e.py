from string import maketrans

def to_rna(dna_strand):
    return dna_strand.translate(maketrans('GCTA','CGAU'))
