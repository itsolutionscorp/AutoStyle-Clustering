TRANSCRIPTIONS = {
    'A': 'A',
    'C': 'C',
    'G': 'G',
    'T': 'U'
}


class DNA(object):
    def __init__(self, sequence):
        self.dna_sequence = sequence
        self.rna_sequence = None

    def to_rna(self):
        if self.rna_sequence is None:
            self.rna_sequence = self._transcribe_to_rna()
        return self.rna_sequence

    def _transcribe_to_rna(self):
        return "".join([TRANSCRIPTIONS[n] for n in self.dna_sequence])
