class DNA(object):

    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        rna = ""
        # Replace each nucleotide with its complement
        for nucleotide in self.dna:
            if nucleotide == "G":
                rna += "C"
            elif nucleotide == "C":
                rna += "G"
            elif nucleotide == "T":
                rna += "A"
            elif nucleotide == "A":
                rna += "U"

        return rna
