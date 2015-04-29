import collections


class DNA(object):
    def __init__(self, strand):
        self.counts = dict.fromkeys('ACTG', 0)
        self.counts.update(collections.Counter(strand))

    def count(self, nucleotide):
        if nucleotide not in 'ACTGU':
            raise ValueError('%s is not a nucleotide.' % nucleotide)
        return self.counts.get(nucleotide, 0)

    def nucleotide_counts(self):
        return self.counts
