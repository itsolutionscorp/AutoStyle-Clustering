class DNA(object):
    RNA_MAP = {'T': 'U'}

    def __init__(self, chain):
        self.chain = chain

    def to_rna(self):
        rna = self.chain
        for (dna_nucleotide, rna_nucleotide) in self.RNA_MAP.items():
            rna = rna.replace(dna_nucleotide, rna_nucleotide)

        return rna
