def to_rna(dna):
    """Converts DNA to RNA by converting nucleotide letters"""

    # Returns False if input is a string and has unexpected characters.
    # Accepts leading and trailing whitespace characters.

    """
    * NUCLEOTIDE
    * DNA -> RNA
    * `G` -> `C`
    * `C` -> `G`
    * `T` -> `A`
    * `A` -> `U`
    """

    if not isinstance(dna, basestring):
        return False

    dna = dna.strip()
    rna = ""

    # n for nucleotide
    for n in dna:
        if n == "G":
            n = "C"
        elif n == "C":
            n = "G"
        elif n == "T":
            n = "A"
        elif n == "A":
            n = "U"
        else:
            # No other input is expected.
            return False
        
        rna += n

    return rna
