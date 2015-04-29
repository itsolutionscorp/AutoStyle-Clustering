def to_rna(strand):
    convert = ''
    for dna in strand:
        if dna == 'G':
            convert += 'C'
        if dna == 'C':
            convert += 'G'
        if dna == 'T':
            convert += 'A'
        if dna == 'A':
            convert += 'U'
    return convert
