def to_rna(DNA):
    return DNA.lower().replace('g', 'C').replace('c', 'G').replace('t', 'A').replace('a', 'U')
