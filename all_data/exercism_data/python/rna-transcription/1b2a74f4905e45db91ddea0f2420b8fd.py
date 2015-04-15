def to_rna(dna):
    translation_table={"C":"G", "G":"C", "A":"U", "T":"A"}
    rna=[]
    for basepair in dna:
        rna.append(translation_table[basepair])
    return ''.join(rna)
