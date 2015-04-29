def to_rna(dna_str):

    dna_to_rna = {'A': 'U', 'T': 'A', 'C': 'G', 'G': 'C'}

    rna = ''.join([dna_to_rna[nucleotide] for nucleotide in dna_str])

    return rna
