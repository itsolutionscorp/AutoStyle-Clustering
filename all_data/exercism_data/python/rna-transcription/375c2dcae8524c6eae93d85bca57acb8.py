def to_rna(dna):
    rna_mapping = {
        "G": "C",
        "C": "G",
        "T": "A",
        "A": "U"}

    return ''.join([rna_mapping[s] for s in dna])
