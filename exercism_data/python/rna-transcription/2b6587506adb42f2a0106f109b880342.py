def to_rna(strand):
    complements = {'G': 'C',
                   'C': 'G',
                   'T': 'A',
                   'A': 'U'}

    result = ''

    for nucleotide in strand:
        result += complements[nucleotide]

    return result
