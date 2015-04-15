import string

# never have been quite sure what the best way to include tables in code is
translation_table = string.maketrans("GCTA","CGAU")
def to_rna(dna_strand):
    return dna_strand.translate(translation_table)
