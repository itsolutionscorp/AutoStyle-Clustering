class DNA:
    DNA_TO_RNA = {
        'C': 'G',
        'T': 'A',
        'A': 'U',
        'G': 'C'
    }

    def __init__(self, dna_string):
        self.dna_string = dna_string

    def to_rna(self):
        return ''.join((self.translate(char) for char in self.dna_string))

    def translate(self, char):
        return self.DNA_TO_RNA[char]
