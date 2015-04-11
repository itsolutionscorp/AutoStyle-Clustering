#
# Author: klemm89
#

#psudeo switch statement
def f(mRNA):
    return {
     	'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }[mRNA]

def to_rna(dna):
	rna = '';
	for mRNA in dna:
		rna += f(mRNA)
	return rna
