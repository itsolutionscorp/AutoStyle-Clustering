"""A DNA strand that can be transcribed into a RNA strand."""

import string


class DNA(object):
    """A DNA strand.

    A DNA strand is a sequence of nucleotides: adenine (A), cytosine (C),
    guanine (G) and thymidine (T).
    """

    def __init__(self, nucleotides):
        self.nucleotides = nucleotides.upper()

    def __len__(self):
        return len(self.nucleotides)

    def __getitem__(self, i):
        return self.nucleotides[i]

    def __setitem__(self, i, nucleotide):
        self.nucleotides[i] = nucleotide.upper()

    def __delitem__(self, i):
        del self.nucleotides[i]

    def __contains__(self, nucleotide):
        return nucleotide.upper() in self.nucleotides

    def __iter__(self):
        return iter(self.nucleotides)

    DNA_TO_RNA = string.maketrans("GCTA", "CGAU")

    def to_rna(self):
        """Return the corresponding transcribed RNA strand."""
        return self.nucleotides.translate(DNA.DNA_TO_RNA)
