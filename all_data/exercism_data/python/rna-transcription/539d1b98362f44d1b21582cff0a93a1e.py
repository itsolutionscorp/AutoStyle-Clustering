def to_rna(prompt):
    RNAOutput = ""
    for letter in prompt:
        # Just in case, we will capitalize
        letter = letter.upper()

        if letter == 'G':
            RNAOutput = RNAOutput + 'C'
        elif letter == 'C':
            RNAOutput = RNAOutput + 'G'
        elif letter == 'T':
            RNAOutput = RNAOutput + 'A'
        elif letter == 'A':
            RNAOutput = RNAOutput + 'U'
        # Should never happen
        else:
            raise Exception("Malformed DNA")

    return RNAOutput
