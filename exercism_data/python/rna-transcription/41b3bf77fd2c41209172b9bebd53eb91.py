from string import maketrans


def to_rna(sequence):
    transcription_map = maketrans("ATGC", "UACG")
    return sequence.translate(transcription_map)
