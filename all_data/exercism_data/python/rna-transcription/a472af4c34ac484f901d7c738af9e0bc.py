_transcription = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}

def to_rna(strand):
    return ''.join(map(lambda n: _transcription[n], strand))
