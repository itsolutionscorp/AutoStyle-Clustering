translate = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

def to_rna(dna):
    rna = ""
    for n in dna:
        rna += translate[n]
    return rna
