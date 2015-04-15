def to_rna(given_string):
    dna_string = list(given_string)
    rna_string = ''

    for letter in dna_string:
        if letter is 'A':
            rna_string += 'U'
        elif letter is 'T':
            rna_string += 'A'
        elif letter is 'C':
            rna_string += 'G'
        elif letter is 'G':
            rna_string += 'C'
        else:
            rna_string += letter

    return rna_string
