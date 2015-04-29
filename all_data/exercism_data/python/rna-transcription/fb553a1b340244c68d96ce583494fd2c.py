__author__ = 'Adam'

def to_rna(input):
    # Make sure that the input is a string.
    assert isinstance(input, str)

    # Set up the variable to return later.
    toreturn = ""

    # Loop through every character.
    for char in input.upper():
        if char == 'A':
            toreturn += 'U'
        if char == 'T':
            toreturn += 'A'
        if char == 'C':
            toreturn += 'G'
        if char == 'G':
            toreturn += 'C'

    # Return the RNA version.
    return toreturn
