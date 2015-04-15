dna_complement = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}


def to_rna(rna):
    return str.join('', [dna_complement[gene] for gene in rna])


def another_to_rna(rna):
    sequence = ''
    for gene in rna:
        sequence += dna_complement[gene]
    return sequence
