RNA_MAP = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
        }
def to_rna(input):
    return "".join([RNA_MAP[x] for x in input])
