def to_rna(dna_strand):
    dna_to_rna = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }

    rna = ''

    for each in dna_strand:
         rna += dna_to_rna[each]

    return rna
