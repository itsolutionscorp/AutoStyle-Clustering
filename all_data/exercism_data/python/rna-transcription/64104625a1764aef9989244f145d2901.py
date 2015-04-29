TRANSCRIPTIONS = {
    'A': 'A',
    'C': 'C',
    'G': 'G',
    'T': 'U'
}


class DNA(object):
    def __init__(self, sequence):
        self.dna_sequence = sequence
        self._rna_sequence = None

    def to_rna(self):
        if self._rna_sequence is None:
            self._rna_sequence = self._transcribe_to_rna()
        return self._rna_sequence

    def _transcribe_to_rna(self):
        return "".join([TRANSCRIPTIONS[n] for n in self.dna_sequence])

    def __str__(self):
        return "DNA[%s]" % (self.dna_sequence,)
