"""A DNA strand."""

from itertools import izip


class DNA(list):
    """A DNA strand.

    A DNA strand is a sequence of nucleotides: adenine (A), cytosine (C),
    guanine (G) and thymidine (T).
    """

    NUCLEOTIDES = ('A', 'C', 'G', 'T')

    def __init__(self, nucleotides):
        """:type nucletides: str."""
        nucleotides = nucleotides.upper()
        for nucleotide in nucleotides:
            if nucleotide not in DNA.NUCLEOTIDES:
                raise ValueError(
                    "{} is not a nucleotide for DNA.".format(nucleotide))
        super(DNA, self).__init__(nucleotides)

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

    def hamming_distance(self, other):
        """Return the minimum number of point mutations between two DNAs."""
        return sum(s != o for s, o in izip(self, other))
