def to_rna(original_dna):

    result = []

    for i in original_dna:
        if i == 'G':
            result.append('C')
        elif i == 'C':
            result.append('G')
        elif i == 'T':
            result.append('A')
        elif i == 'A':
            result.append('U')

    return "".join(result)
