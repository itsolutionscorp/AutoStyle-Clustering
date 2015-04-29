def to_rna(dna):
    '''replaces G<-->C, A-->U, T-->A '''
    translated = []
    for basepair in dna:
        # print basepair,
        if basepair == 'G':
            translated.append('C')
        elif basepair == 'C':
            translated.append('G')
        elif basepair == 'A':
            translated.append('U')
        elif basepair == 'T':
            translated.append('A')
        else:
            return dna
    return ''.join(translated)
