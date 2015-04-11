#
#* `G` -> `C`
#* `C` -> `G`
#* `T` -> `A`
#* `A` -> `U`
#
#  Check the input
#  Make it a list
#  transcribe the list
#  Make it a string and return

def to_rna(dna):
	i = 0 # index counter
	rna = '' # RNA string
	while i < len(dna):        # Loop through the string and 
		if dna[i] == 'G':      # see what the letter is, if we 
			rna = rna + 'C'    # get a match, replace and go to the next
			i = i + 1
			continue
		elif dna[i] == 'C':
			rna = rna + 'G'
			i = i + 1
			continue
		elif dna[i] == 'T':
			rna = rna + 'A'
			i = i + 1
			continue
		elif dna[i] == 'A':
			rna = rna + 'U'
			i = i + 1
			continue
		else:
			rna = rna + dna[i]		
			pos = str(i)
			print 'Error at char ' + pos + ' expected DNA sequence chars'
			i = i + 1	
	return rna
	
	
