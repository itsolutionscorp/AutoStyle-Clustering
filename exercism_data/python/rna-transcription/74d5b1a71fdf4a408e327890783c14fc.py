class DNA:
    _transcriptions = {
            'G': 'C',
            'C': 'G',
            'T': 'A',
            'A': 'U'
            }

    def __init__(self, sequence):
        self.sequence = sequence 

    def to_rna(self):
        return ''.join([DNA._transcriptions[n] for n in self.sequence])
