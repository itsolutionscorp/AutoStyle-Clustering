change = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

def to_rna(dna):
    rna_nucleotide = '' 
    for dna_nucleotide in dna:
        rna_nucleotide += change[dna_nucleotide]

    return rna_nucleotide
