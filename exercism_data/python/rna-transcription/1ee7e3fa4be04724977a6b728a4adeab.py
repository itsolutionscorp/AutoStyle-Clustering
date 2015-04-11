"""
Exercise 4: RNA transcription
"""

def to_rna(dna):
    """
    Takes the string 'dna' and calls the generator
    'transcribe' to convert each nucleotide of the chain
    individually. Joins into one string.
    """
    result = ''
    for letter in transcribe(dna):
        result = ''.join([result, letter])
    return result


def transcribe(chain):
    """
    Generator to convert individual nucleotides into
    their compliment one by one.
    """
    for link in chain:
        if link == 'G':
            yield 'C'
        elif link == 'C':
            yield 'G'
        elif link == 'T':
            yield 'A'
        elif link == 'A':
            yield 'U'
