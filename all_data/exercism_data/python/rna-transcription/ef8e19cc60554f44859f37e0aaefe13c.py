def to_rna(dna):
    rna_code = ''
    trascription_key = {
            'G':'C',
            'C':'G',
            'T':'A',
            'A':'U',
            }
    try:
        for i in dna:
            rna_code += trascription_key[i]
        return rna_code
    except KeyError:
        return('%s is not a valid DNA code' % (dna))
