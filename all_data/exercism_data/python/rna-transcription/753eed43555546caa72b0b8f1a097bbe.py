def to_rna(dna):
    convert = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    if len(dna) <= 1:
        return convert[dna]
    else:
        string_dna = ''
        for l in range(0, len(dna)):
            string_dna += convert[dna[l]]
        return string_dna
