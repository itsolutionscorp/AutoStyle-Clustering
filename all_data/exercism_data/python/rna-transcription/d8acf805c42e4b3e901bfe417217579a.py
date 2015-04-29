def to_rna(sequence):
    converter = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    output = []
    for nucleo in sequence:
        output.append(converter[nucleo])
    return ''.join(output)
