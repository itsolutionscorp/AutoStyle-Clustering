def to_rna(sequence):
    result = ''
    for letter in sequence:
        if letter == 'G':
            result += 'C'
        if letter == 'C':
            result += 'G'
        if letter == 'T':
            result += 'A'
        if letter == 'A':
            result += 'U'
    return result
