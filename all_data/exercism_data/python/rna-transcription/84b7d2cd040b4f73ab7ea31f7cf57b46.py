import string

transcription = string.maketrans('GCTA', 'CGAU')


def to_rna(secuence):
    return secuence.translate(transcription)
