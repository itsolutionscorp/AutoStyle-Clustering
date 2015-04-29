def to_rna(seq):

    seq = seq.replace('G', 'X')
    seq = seq.replace('C', 'G')
    seq = seq.replace('X', 'C')
    seq = seq.replace('A', 'U')
    seq = seq.replace('T', 'A')

    return seq
