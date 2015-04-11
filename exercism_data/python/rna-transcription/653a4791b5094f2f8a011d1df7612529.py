def to_rna(strand):
    d_to_r = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    return ''.join([d_to_r[c] for c in strand])

if __name__ == '__main__':
    print to_rna('C')
    print to_rna('ACGTGGTCTTAA')
