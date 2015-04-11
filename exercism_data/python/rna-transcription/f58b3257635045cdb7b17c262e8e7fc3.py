def to_rna(DNA_strand):
    RNA_trans = []
    for nuc in DNA_strand:
        if nuc == 'G':
            RNA_trans.append('C')
        elif nuc == 'C':
            RNA_trans.append('G')
        elif nuc == 'T':
            RNA_trans.append('A')
        else:   #expecting nuc == 'A'
            RNA_trans.append('U')
    return "".join(RNA_trans)
