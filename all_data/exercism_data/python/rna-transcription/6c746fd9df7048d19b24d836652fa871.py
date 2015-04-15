import string

#* `G` -> `C`
#* `C` -> `G`
#* `T` -> `A`
#* `A` -> `U`

TABLE = string.maketrans('GCTA', 'CGAU')

def to_rna(d):
    return d.translate(TABLE)   
