def to_rna(strand):
    tr = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    return ''.join([tr[nucl] for nucl in strand])
