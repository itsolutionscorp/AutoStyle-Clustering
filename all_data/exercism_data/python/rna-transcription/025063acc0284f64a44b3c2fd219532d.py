def to_rna(bases):
    """ transcribes DNA bases to RNA complement
    Input: String of DNA bases(5'-3')
    Output: String of complementary RNA bases(3'-5' NONSTANDARD!)
    """
    rna_bases = list()
    for base in bases:
        rna_bases.append(rna_base(base))
    return "".join(rna_bases)
def rna_base(dna):
    if dna == "A":
        return "U"
    if dna == "T":
        return "A"
    if dna == "G":
        return "C"
    if dna == "C":
        return "G"
