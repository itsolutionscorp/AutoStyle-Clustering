def to_rna(strand):
    eqs = {'G': 'C', "C": "G", "T": "A", "A": "U"}
    return "".join([eqs[c] for c in strand])
