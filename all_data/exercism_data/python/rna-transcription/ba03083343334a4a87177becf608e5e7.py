class DNA(object):

    def __init__(self, sequence):
        self.complements = {
            'G': 'C',
            'C': 'G',
            'T': 'A',
            'A': 'U',
        }
        # input validation
        if not isinstance(sequence, basestring):
            raise Exception('Sequence must be a string')
        self.dna_sequence = [c for c in sequence if c in self.complements]

    def to_rna(self):
        return ''.join([self.complements.get(c) for c in self.dna_sequence])
