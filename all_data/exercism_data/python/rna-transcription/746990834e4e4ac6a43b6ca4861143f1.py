import string

transcription = string.maketrans('GCTA', 'CGAU')


def to_rna(sequence):
    return sequence.translate(transcription)
