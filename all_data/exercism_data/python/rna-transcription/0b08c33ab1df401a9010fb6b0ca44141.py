# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`
import string 

def to_rna(rna):
	r = string.maketrans("GCTA","CGAU")
	return string.translate(rna,r)
