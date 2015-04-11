import re

class DNA:
    dna_string = ""

    def __init__(self, dna_string):
        self.dna_string = dna_string
    
    def to_rna(self):
        rna_string = re.sub(r'T', r'U', self.dna_string)
        return rna_string
