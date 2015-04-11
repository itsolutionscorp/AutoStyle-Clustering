def to_rna(strand):
    """Converts DNA strand to its complement RNA strand"""

    nucleotide = "GCTA"
    complement = "CGAU"

    rna_list = [complement[nucleotide.find(n)] for n in strand]
    return ''.join(rna_list)
