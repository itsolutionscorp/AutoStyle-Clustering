TRANSLATION_MAP = {
        'C': 'G',
        'G': 'C',
        'T': 'A',
        'A': 'U',
        }
def to_rna(inputString):
    outputString = ''
    for letter in inputString:
        outputString += TRANSLATION_MAP[letter]

    return outputString
