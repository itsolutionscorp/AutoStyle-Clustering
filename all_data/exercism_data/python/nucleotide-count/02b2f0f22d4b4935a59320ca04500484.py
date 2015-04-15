from collections import Counter


class DNA(object):

    _nucleotides = {n: 0 for n in 'ACGT'}

    def __init__(self, strand):
        self.strand = strand
        self._counts = Counter(strand)
        # make sure that all nucleotides are keys in self._counts
        self._counts.update(self._nucleotides)

        # make sure that strand is valid DNA
        if len(self._counts) > 4:
            bad_nucleotide = next(n for n in self._counts
                                  if n not in self._nucleotides)
            raise ValueError("Invalid nucleotide {}".format(bad_nucleotide))

    def nucleotide_counts(self):
        return self._counts

    def count(self, nucleotide):
        if (nucleotide in self._nucleotides) or (nucleotide == 'U'):
            return self._counts[nucleotide]
        raise ValueError("{} is not a nucleotide.".format(nucleotide))
