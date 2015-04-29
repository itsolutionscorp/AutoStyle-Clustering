class DNA:

    def __init__(self, value = ''):
        self.dna_sequence = value

    def to_rna(self):
        new_sequence = ''
        for letter in self.dna_sequence:
            if letter == 'G':
                new_sequence += 'C'
            elif letter == 'C':
                new_sequence += 'G'
            elif letter == 'T':
                new_sequence += 'A'
            elif letter == 'A':
                new_sequence += 'U'

        return new_sequence 
