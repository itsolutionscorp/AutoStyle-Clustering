DNA_TO_RNA_MAP = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}


def to_rna(dna_strand):
    return "".join(
        map(lambda dna_char: DNA_TO_RNA_MAP[dna_char], dna_strand)
    )
