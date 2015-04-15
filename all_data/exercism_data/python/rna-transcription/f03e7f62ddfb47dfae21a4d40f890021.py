from string import *
def to_rna(d_code):
	old_code='GCTA'
	new_code='cgau'
	for i in xrange(4):
		d_code=replace(d_code,old_code[i],new_code[i])
	return upper(d_code)
	
