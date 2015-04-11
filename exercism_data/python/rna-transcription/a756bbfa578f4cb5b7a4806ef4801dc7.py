def to_rna(seq):
    seq = seq.replace("C", "\x00")
    seq = seq.replace("G", "C")
    seq = seq.replace("\x00", "G")
    seq = seq.replace("A", "U")
    seq = seq.replace("T", "A")
    return seq
