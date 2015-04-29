'''
* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U`
'''
mapping ={'G':'C',
		  'C':'G',
		  'T':'A',
		  'A':'U'
  }
import re
def subFunc(matchobj):
	return mapping[matchobj.group(0)]
def to_rna(dna):
	return re.sub('[GCTA]',subFunc, dna)
