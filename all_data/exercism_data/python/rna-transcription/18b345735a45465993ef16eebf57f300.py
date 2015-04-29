def to_rna(dna_sequence):
    return DnaTranscriptor().transcribe(dna_sequence)

class DnaTranscriptor(object):

    def __init__(self, translation_table=str.maketrans('GCTA', 'CGAU')):
        self.translation_table = translation_table

    def transcribe(self, dna_sequence):
        return dna_sequence.translate(self.translation_table)
