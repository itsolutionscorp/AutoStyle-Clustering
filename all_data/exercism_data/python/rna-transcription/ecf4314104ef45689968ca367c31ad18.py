def to_rna(dna):
    """Converts a DNA strand into its equivalent RNA strand."""

    new_string = []

    for char in dna:
        new_char = {
            'G' : 'C',
            'C' : 'G',
            'T' : 'A',
            'A' : 'U'
        }[char]

        new_string.append(new_char)

    return ''.join(new_string)
