import StringIO

def to_rna(strand):
    rna = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }
    s = StringIO.StringIO()

    for letter in strand:
        s.write(rna[letter])

    return s.getvalue()
