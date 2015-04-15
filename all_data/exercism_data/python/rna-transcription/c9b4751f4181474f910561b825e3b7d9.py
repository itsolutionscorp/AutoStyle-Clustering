def to_rna(txt):
    return "".join(['A' if s == 'T' else 'U' if s == 'A' else 'C' if s == 'G' else 'G' for s in txt])
