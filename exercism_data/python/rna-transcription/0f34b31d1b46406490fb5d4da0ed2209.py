class DNA:
    """DNA behaviour representation.

    Attributes:
        __dna_strand     The DNA strand passed in __init__
        __dna_to_rna_map Translation map from DNA to RNA
    """
    __dna_strand = ""
    __dna_to_rna_map = {
        "G" : "C",
        "C" : "G",
        "T" : "A",
        "A" : "U",
    }
    def __init__(self, dna_strand):
        self._dna_strand = dna_strand

    def to_rna(self):
        """Transforms a DNA strand to a RNA strand."""
        rna_strand = ""

        for dna_nucleotide in self._dna_strand:
            rna_strand += self.__dna_to_rna_map[dna_nucleotide]

        return rna_strand
