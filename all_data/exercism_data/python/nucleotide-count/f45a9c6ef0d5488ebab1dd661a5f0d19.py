class DNA(object):
    def __init__(self, dnaString):
        counts = {
            "G": 0,
            "C": 0,
            "T": 0,
            "A": 0,
        }

        for nucleotide in dnaString.upper():
            counts[nucleotide] += 1

        self.counts = counts

    def nucleotide_counts(self):
        return self.counts

    def count(self, nucleotide):
        # Special case for nucleotide that does not appear in DNA
        if nucleotide.upper() == "U":
            return 0

        try:
            return self.counts[nucleotide]
        except KeyError:
            # Raise particular error for non-nucleotide inputs
            raise ValueError("%s is not a nucleotide." %nucleotide)
