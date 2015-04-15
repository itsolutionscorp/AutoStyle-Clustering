def to_rna(dna):
    complement = dict(G='C',C='G',T='A',A='U')
    return ''.join([complement[nucleotide] for nucleotide in dna])
