def to_rna(DNA):
    translation = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }

    RNA = ''
    for c in DNA:
        if c in translation.keys():
            RNA += translation[c]
        else:
            RNA += c
    return RNA
