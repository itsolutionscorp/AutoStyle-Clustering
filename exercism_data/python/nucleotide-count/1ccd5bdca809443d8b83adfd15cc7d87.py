from collections import Counter


class DNA:

    _NUCLEOTIDES_DNA = 'ATCG'
    _NUCLEOTIDES_RNA = 'ACGU'

    def __init__(self, strand):
        self.strand = strand
        self.counter = Counter(strand)

    def count(self, nucleotide):
        if nucleotide not in (set(self._NUCLEOTIDES_DNA) | set(self._NUCLEOTIDES_RNA)):
            raise ValueError(nucleotide + ' is not a nucleotide.')
        return self.counter[nucleotide]

    def nucleotide_counts(self):
        cnt_as_dict = {item[0]: item[1] for item in self.counter.items()}
        for n in self._NUCLEOTIDES_DNA:
            if n not in self.counter.keys():
                cnt_as_dict[n] = 0
        return cnt_as_dict
