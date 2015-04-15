"""A DNA strand that can be transcribed into a RNA strand."""

import string


class DNA(list):
    """A DNA strand.

    A DNA strand is a sequence of nucleotides: adenine (A), cytosine (C),
    guanine (G) and thymidine (T).
    """

    def __init__(self, nucleotides):
        """:type nucletides: str."""
        super(DNA, self).__init__(nucleotides.upper())

    def __contains__(self, nucleotide):
        """:type nucleotide: str."""
        nucleotide = nucleotide.upper()
        nucleotide_len = len(nucleotide)
        if nucleotide_len == 1:
            return super(DNA, self).__contains__(nucleotide)
        elif nucleotide_len >= 2:
            return nucleotide in "".join(self)
        else:                   # nucleotide_len == 0
            return True

    def __repr__(self):
        return "{!s}({!r})".format(self.__class__.__name__, "".join(self))

    def __str__(self):
        return "".join(self)

    DNA_TO_RNA = string.maketrans("GCTA", "CGAU")

    def to_rna(self):
        """Return the corresponding transcribed RNA strand."""
        return "".join(self).translate(DNA.DNA_TO_RNA)
