COMPLEMENT = {
    "G": "C",
    "C": "G",
    "T": "A",
    "A": "U"
}


def to_rna(nucleotides):
    dna_list = list(nucleotides)
    rna_list = [COMPLEMENT.get(i) for i in dna_list]
    return "".join(rna_list)
