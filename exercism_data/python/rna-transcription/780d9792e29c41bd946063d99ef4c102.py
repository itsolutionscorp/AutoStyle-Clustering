default_translation = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

class DnaTransciptor(object):

    def __init__(self, translation=default_translation):
        self.translation = translation

    def transcribe(self, dna_strand):
        primary_transcript = []
        for dna_nucleotides in dna_strand:
            primary_transcript.append(self.translation[dna_nucleotides])
        return ''.join(primary_transcript)


def to_rna(dna_sequence):
    transcriptor = DnaTransciptor()
    return transcriptor.transcribe(dna_sequence)
