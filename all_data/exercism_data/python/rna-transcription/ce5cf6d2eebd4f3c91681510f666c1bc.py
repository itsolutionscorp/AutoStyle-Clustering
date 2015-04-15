def to_rna(dna):
    rna = ""
    nucleotide_dna = "GCTA"
    nucleotide_rna = "CGAU"
    for nucleotide in dna:
        type = nucleotide_dna.index(nucleotide)
        rna += nucleotide_rna[type]
    return rna
