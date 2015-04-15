class DNA(object):
    def __init__(self, dna_string):
        self.dna_string = dna_string


    def to_rna(self):
        rna_string = ''
        for nucleotide in self.dna_string:
            if nucleotide.upper() == 'T':
                rna_string += 'U'
            else:
                rna_string += nucleotide.upper()
        return rna_string

if __name__ == "__main__":
    pass
