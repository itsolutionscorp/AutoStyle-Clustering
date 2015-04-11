class DNA():

    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        ret = ''
        for letter in self.dna:
            if letter == 'G':
                ret += 'C'
            elif letter == 'C':
                ret += 'G'
            elif letter == 'T':
                ret += 'A'
            elif letter == 'A':
                ret += 'U'

        return ret
