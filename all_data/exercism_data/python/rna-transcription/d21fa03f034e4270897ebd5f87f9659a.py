def __to_rna_single_char(dna_char):
    return {
        "G": "C",
        "C": "G",
        "T": "A",
        "A": "U"
    }[dna_char]


def to_rna(dna):
    return ''.join([__to_rna_single_char[x] for x in dna])
