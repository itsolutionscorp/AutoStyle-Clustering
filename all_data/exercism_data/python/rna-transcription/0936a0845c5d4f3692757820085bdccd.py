def to_rna(dna):
    rna = []
    dict = {'A': 'U', 'C': 'G', 'T': 'A', 'G': 'C'}
    for letter in dna:
        rna.append(dict[letter])

    return ''.join(rna)
