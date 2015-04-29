def to_rna(string):

    dna_to_rna = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    return_rna = []
    for letter in string:
        return_rna.append(dna_to_rna[letter])

    return ''.join(return_rna)
