def to_rna(rna):
    string_result = ''
    for character in rna:
        print(character)
        if character == 'G':
            string_result += 'C'
        elif character == 'C':
            string_result += 'G'
        elif character == 'T':
            string_result += 'A'
        elif character == 'A':
            string_result += 'U'
        else:
            string_result += character
    return string_result
