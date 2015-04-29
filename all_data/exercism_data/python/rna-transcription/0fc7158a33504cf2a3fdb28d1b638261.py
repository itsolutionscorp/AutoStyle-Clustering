dna_rna_map = {
    "C": "G",
    "G": "C",
    "T": "A",
    "A": "U",
}


def to_rna(dna):
    return "".join([dna_rna_map[nucleotide] for nucleotide in dna])
