"""
converts a given dna strand to rna complement
"""
def to_rna(dna_strand):
    # rna complements
    complements = {
        "G": "C",
        "C": "G",
        "A": "U",
        "T": "A"
    }

    rna_strand = ""
    for nucleotide in dna_strand:
        # assume valid dna_strand has valid complement
        rna_strand += complements[nucleotide]
    return rna_strand
