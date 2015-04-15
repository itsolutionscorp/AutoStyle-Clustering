DNA_TO_RNA_MAP = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}


def char_to_rna(dna_char):
    return DNA_TO_RNA_MAP.get(dna_char, dna_char)


def to_rna(dna_strand):

    return "".join(
        map(char_to_rna, dna_strand)
    )
