def to_rna(dna):

    rna = ''
    
    for n in dna:

        if n=='G':
            rna += 'C'
            continue

        elif n== 'C':
            rna += 'G'
            continue

        elif n=='T':
            rna += 'A'
            continue

        else :
            rna += 'U'

    return rna

        

            

    
