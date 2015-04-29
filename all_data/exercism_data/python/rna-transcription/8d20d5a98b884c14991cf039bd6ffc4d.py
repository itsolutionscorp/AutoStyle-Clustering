def to_rna(dna):
    rna = dna.replace('G', 'tc')
    rna = rna.replace('C', 'G')
    rna = rna.replace('T', 'ta')
    rna = rna.replace('A', 'U')
    rna = rna.replace('tc', 'C').replace('ta', 'A')
    return rna
