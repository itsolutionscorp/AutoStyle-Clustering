def to_rna(what):
    trans = {
        'A': 'U',
        'C': 'G',
        'G': 'C',
        'T': 'A',
    }

    this = ''
    for a in what:
        this += trans[a]

    return this;
