"""A DNA strand."""

from collections import Counter


NUCLEOTIDES = ('A', 'C', 'G', 'T', 'U')


class DNA(str):
    """A DNA strand.

    A DNA strand is a sequence of nucleotides: adenine (A), cytosine (C),
    guanine (G) and thymidine (T).
    """

    NUCLEOTIDES = ('A', 'C', 'G', 'T')

    def __init__(self, nucleotides):
        nucleotides = nucleotides.upper()
        for nucleotide in nucleotides:
            if nucleotide not in DNA.NUCLEOTIDES:
                raise ValueError(
                    "{} is not a nucleotide for DNA.".format(nucleotide))
        super(DNA, self).__init__(nucleotides)
        self._counter = None

    def __contains__(self, nucleotides):
        nucleotides = nucleotides.upper()
        return super(DNA, self).__contains__(nucleotides)

    def __repr__(self):
        return "{!s}({!r})".format(self.__class__.__name__, str(self))

    def nucleotide_counts(self):
        """Return a nucleotide frequency dictionary."""
        if self._counter is None:
            self._counter = Counter({'A': 0, 'C': 0, 'G': 0, 'T': 0})
            self._counter.update(self)
        return self._counter

    def count(self, nucleotide):
        """Return the frequency."""
        nucleotide = nucleotide.upper()
        if nucleotide not in NUCLEOTIDES:
            raise ValueError("{} is not a nucleotide.".format(nucleotide))
        return self.nucleotide_counts()[nucleotide]
