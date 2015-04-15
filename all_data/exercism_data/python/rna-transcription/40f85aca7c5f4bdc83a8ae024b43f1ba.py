def to_rna(dna):
    conversion = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }

    rna = []
    for nucleotid in dna:
        rna.append(conversion[nucleotid])

    return ''.join(rna)
