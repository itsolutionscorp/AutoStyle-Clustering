def to_rna( dna_strand ):
    translation = str.maketrans('GCTA','CGAU')
    return dna_strand.translate( translation )
