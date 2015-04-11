def to_rna(dna):
    rna = ''

    for nu in dna:
        if nu == 'G':
            rna += 'C'
        elif nu == 'C':
            rna += 'G'
        elif nu == 'T':
            rna += 'A'
        elif nu == 'A':
            rna += 'U'
        else:
            print "Unknown nucleotide: %s" % nu
            rna += nu

    return rna
