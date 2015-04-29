transcription = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}

def to_rna(strand):
    rna = ''
    for char in strand:
        rna += transcription[char]

    return rna
