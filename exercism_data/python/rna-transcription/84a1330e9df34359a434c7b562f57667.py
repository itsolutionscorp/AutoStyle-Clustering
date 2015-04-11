def to_rna(dna_seq):
    rna_seq = dna_seq.replace('A', 'U').replace('T', 'A')
    return rna_seq.replace('C', 'g').replace('G', 'C').replace('g', 'G')
