class DNA(object):

    RNA_MAP = {'C':'C', 'G':'G', 'A':'A', 'T':'U'}

    def __init__(self, dna_strand):
        self.dna_strand = dna_strand
        pass

    def _translate(self, dna):
        try:
            return self.RNA_MAP[dna.upper()]
        except KeyError:
            raise Exception("Not a valid DNA String")

    def to_rna(self):
        return ''.join([self._translate(dna) for dna in self.dna_strand])
