#
# exercism.io code challange
#
# Joshua Ferdaszewkski
# Jan 14, 2015
#

import string

def to_rna(dna):   
    return dna.translate(string.maketrans('GCTA', 'CGAU'))
