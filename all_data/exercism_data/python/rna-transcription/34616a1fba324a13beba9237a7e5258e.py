from string import maketrans

dna_nucleotides = 'GCTA'
rna_nucleotides = 'CGAU'
translation_table = maketrans(dna_nucleotides, rna_nucleotides)

def to_rna(dna):
    return dna.translate(translation_table)
    
