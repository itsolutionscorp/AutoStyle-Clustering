def to_rna(input):
    inputSequence = list(input)
    outputSequence = []
    converterSequence = {'G': 'C','C': 'G','T': 'A','A': 'U'}
    for i in inputSequence:
        outputSequence.extend(converterSequence[i])
    return ''.join(outputSequence)
