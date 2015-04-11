# transcribes dna to RNA A->U

def complement(nuc):

    if nuc == 'G':

         return 'C'

    if nuc == 'C':

        return 'G'

    if nuc == 'T':

        return 'A'

    if nuc == 'A':

        return 'U'


def to_rna(dna):

    rna =''

    for nuc in dna:

        rna += complement(nuc)
        
    return rna
