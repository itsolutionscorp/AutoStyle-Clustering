def to_rna(dna):
    transcriptions = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    rna = str()

    for c in dna:
        rna = rna + transcriptions[c]

    return rna
