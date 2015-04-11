TRANSLATION_MAP = {
        'C': 'G',
        'G': 'C',
        'T': 'A',
        'A': 'U',
        }
def to_rna(inputString):
    return ''.join(map(lambda x: TRANSLATION_MAP[x], inputString))
