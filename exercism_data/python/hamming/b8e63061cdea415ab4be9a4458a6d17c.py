def hamming(a_dna, b_dna):
    '''Input two strings of dna and return an integer of the number of differences.'''
    pairs = zip(a_dna, b_dna)
    return sum([1 for a,b in pairs if a != b]) + abs(len(a_dna) - len(b_dna))
