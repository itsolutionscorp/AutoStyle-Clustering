from string import maketrans

def to_rna(input_str):
    conversion = maketrans('GCTA', 'CGAU')
    result = input_str.translate(conversion)
    return result
