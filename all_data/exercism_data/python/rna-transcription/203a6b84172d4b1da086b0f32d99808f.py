def to_rna(strand):
    # The blueprint for translating DNA to RNA
    translate = { 'G' : 'C',
                  'C' : 'G',
                  'T' : 'A',
                  'A' : 'U' }

    rna = ''
    # Translate each nucleotide to its RNA counterpart
    for nucleotide in strand:
        rna += translate[nucleotide]

    return rna
