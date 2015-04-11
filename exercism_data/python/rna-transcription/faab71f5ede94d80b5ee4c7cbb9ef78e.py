switch = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

def to_rna(strand):
    rna = ""
    for c in strand:
        rna += switch[c]
    return rna
