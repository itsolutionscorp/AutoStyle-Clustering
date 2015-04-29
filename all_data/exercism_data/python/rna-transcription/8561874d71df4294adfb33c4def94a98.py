def to_rna(seq):
    seq = seq.replace("C", "_")
    seq = seq.replace("G", "C")
    seq = seq.replace("_", "G")
    seq = seq.replace("A", "U")
    seq = seq.replace("T", "A")
    return seq
