strand = {'G':'C', 'C':'G', 'T':'A' ,'A':'U'}

def to_rna(sequence):
    out = ""
    for s in sequence:
        out = out + strand[s]
    return out
