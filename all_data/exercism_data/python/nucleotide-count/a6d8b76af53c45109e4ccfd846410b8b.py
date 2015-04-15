class DNA(str):
    def count(self, nucleotide):
        if not self.is_nucleotide(nucleotide):
            raise ValueError('%c is not a nucleotide.' % nucleotide)
        return super(DNA, self).count(nucleotide)

    def nucleotide_counts(self):
        return dict((n,self.count(n)) for n in 'ACGT')

    @classmethod
    def is_nucleotide(cls, nucleotide):
        return nucleotide in 'ACGTU'
