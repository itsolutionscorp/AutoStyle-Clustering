def to_rna(s):
    s = s.replace("G", "C")
    s = s.replace("C", "G")
    s = s.replace("T", "A")
    s = s.replace("A", "U")
    return s

