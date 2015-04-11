def to_rna(dna):
    transcribe = dict(G='C',C='G',T='A',A='U')
    return str().join([transcribe[nucleotide] for nucleotide in dna])
