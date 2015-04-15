def to_rna(dna):
    complements = { 'G':'C', 'C':'G', 'T':'A', 'A':'U' }
    rna = [complements[d] for d in dna]
    return "".join(rna)
