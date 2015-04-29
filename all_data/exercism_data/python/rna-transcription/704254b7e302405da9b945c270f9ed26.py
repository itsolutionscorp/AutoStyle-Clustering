def to_rna(dna_strand):
    complements = {'A': 'U', 'C': 'G', 'T': 'A', 'G': 'C'}
    return ''.join(complements[dna_nucleotide] for dna_nucleotide in dna_strand\
                   if dna_nucleotide in complements.keys())
