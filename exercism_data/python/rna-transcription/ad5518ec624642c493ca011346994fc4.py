nucDNA = 'GCTA'
nucRNA = 'CGAU'

# Adds the complement RNA using index of DNA and RNA
def to_rna(base):
    nb = ''
    for nuc in base:
        nb += nucRNA[nucDNA.find(nuc)]
    return nb
        
    
