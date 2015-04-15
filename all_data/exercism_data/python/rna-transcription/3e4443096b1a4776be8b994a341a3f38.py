def to_rna(input_string):
    new_string = ''

    for letter in input_string:
        if letter is 'G':
            new_string += 'C'
        elif letter is 'C':
            new_string += 'G'
        elif letter is 'T':
            new_string += 'A'
        elif letter is 'A':
            new_string += 'U'

    return new_string
