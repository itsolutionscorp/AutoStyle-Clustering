complements = {
    'G':'C',
    'C':'G',
    'T':'A',
    'A':'U'
}

def to_rna(dna):
    rna = [complements[d] for d in dna]
    return "".join(rna)
