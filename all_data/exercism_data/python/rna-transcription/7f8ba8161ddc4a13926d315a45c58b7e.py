def to_rna(dna):
    transcribe = dict(G='C',C='G',T='A',A='U')
    rna = ''
    for nucleotide in dna:
        rna += transcribe[nucleotide]
    return rna
