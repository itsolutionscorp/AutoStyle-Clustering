def to_rna(strand):
    strand = strand.lower()
    strand = strand.replace('g', 'C')
    strand = strand.replace('c', 'G')
    strand = strand.replace('t', 'A')
    strand = strand.replace('a', 'U')
    return strand
