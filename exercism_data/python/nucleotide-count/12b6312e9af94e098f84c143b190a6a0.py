class DNA(object):
    def __init__(self, sequence):
        self.counts = {'A':0,'C':0,'G':0,'T':0}
        for nucleotide in sequence:
            if nucleotide in self.counts:
                self.counts[nucleotide] += 1
            elif nucleotide == 'U':
                continue
            else:
                raise ValueError

    def nucleotide_counts(self):
        return self.counts

    def count(self, nucleotide):
        if nucleotide in self.counts:
            return self.counts[nucleotide]
        elif nucleotide == 'U':
            return 0
        else:
            raise ValueError('{} is not a nucleotide.'.format(nucleotide))
