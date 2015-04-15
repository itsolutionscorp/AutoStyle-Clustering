def switch_char(c):
    if c == 'G':
        return 'C'
    if c == 'C':
        return 'G'
    if c == 'T':
        return 'A'
    if c == 'A':
        return 'U'
    return '!'

def to_rna(string):
    return ''.join([switch_char(char) for char in string])
