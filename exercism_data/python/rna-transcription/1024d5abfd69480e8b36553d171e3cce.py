def to_rna(str):
    out = []
    for letter in str:
        if letter in trans:
            out.append(trans[letter])

    return ''.join(out)


trans = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}
