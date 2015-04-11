def distance(seq1,seq2):
	counter=0
	if len(seq1)==len(seq2):
		for i in range(0, len(seq1)):
			if seq1[i]!=seq2[i]:
				counter+=1
			else:
				continue
	else:
		print "The strings are not equal"
			
	return(counter)
    				
    	
