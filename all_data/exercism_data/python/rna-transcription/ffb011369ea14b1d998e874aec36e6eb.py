import re

def to_rna(strand):
    transcriptions = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
        }
    pattern = re.compile('|'.join(transcriptions.keys()))
    return pattern.sub(lambda x: transcriptions[x.group()], strand)
