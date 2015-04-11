'''
DNA Manipulation
'''
import string

DNA = 'ACGT'
RNA = 'UGCA'

TRANSCRIPTION = string.maketrans(DNA, RNA)

def to_rna(dna):
    '''
    Transcribe DNA to RNA
    '''
    if not valid_dna(dna):
        raise RuntimeError('DNA sequence invalid')

    return dna.translate(TRANSCRIPTION)


def valid_dna(dna):
    '''
    Check the validity of a DNA sequence
    '''
    empty = not dna
    invalidcodes = dna.translate(None, DNA)

    return not empty and not invalidcodes
