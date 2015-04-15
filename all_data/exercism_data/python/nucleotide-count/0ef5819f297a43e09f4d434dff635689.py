class DNA(object):

    """Simple DNA counting utility. """

    def __init__(self, string):
        """Sets up the initial nucleotide counts.

        :string: The DNA string to investigate.

        """
        self._string = string
        self._nucleotides = {'A': 0, 'T': 0, 'C': 0, 'G': 0}

    def count(self, nucleotide):
        """Counts the occurence of nucleotide in current string.

        :nucleotide: The nucleotide to count.

        """
        if nucleotide in self._nucleotides.keys() or nucleotide == 'U':
            return self._string.count(nucleotide)
        else:
            raise ValueError(nucleotide + " is not a nucleotide.")

    def nucleotide_counts(self):
        """Returns all the corresponding nucleotide counts.

        """
        for nucleotide in self._string:
            self._nucleotides[nucleotide] += 1
        return self._nucleotides
