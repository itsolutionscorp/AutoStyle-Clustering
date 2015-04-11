from collections import Counter

class DNA(object):
    dna_nucleotides = set('ACGT')
    rna_nucleotides = set('ACGU')

    def __init__(self, dna_sequence):
        self.dna_sequence = dna_sequence

    def count(self, nucleotide):
        """Count how many times given nucleotide appears in sequence.
           Only allow valid nucleotides."""
        if nucleotide in (self.dna_nucleotides | self.rna_nucleotides):
            return self.dna_sequence.count(nucleotide)
        raise ValueError('{0} is not a nucleotide.'.format(nucleotide))

    def nucleotide_counts(self):
        """Return dict of all nucleotide counts in sequence."""
        counter = Counter()
        for nucleotide in self.dna_nucleotides:
            counter[nucleotide] = 0
        counter.update(self.dna_sequence)
        return dict(counter)
