from string import maketrans

dna = "GCTA"
rna = "CGAU"
dna_rna_table = maketrans(dna, rna)


def to_rna(strand):
    """
    Given a DNA strand, its transcribed RNA strand is formed by replacing
    each nucleotide with its complement:

    * `G` -> `C`
    * `C` -> `G`
    * `T` -> `A`
    * `A` -> `U`
    """
    return strand.translate(dna_rna_table)
