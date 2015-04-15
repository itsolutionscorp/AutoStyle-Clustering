def to_rna(dna_sequence):
    translation = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    result= [translation[nucleotide] for nucleotide in dna_sequence]
    return ''.join(result)
