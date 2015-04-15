def to_rna(dna):
    if len(dna) == 1:
        for d, r in {'G':'C', 'C':'G', 'T':'A', 'A':'U'}.items():
            dna = dna.replace(d, r)
        return dna

    else:
        for d, r in {'G':'C', 'C':'G', 'T':'A', 'A':'U'}.items():  
            dna = dna.replace(d, r)
        return dna
