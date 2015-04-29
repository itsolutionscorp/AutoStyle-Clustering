def compute(seq1=nil, seq2=nil)
	# seq1 and seq2 must be strings consisting of one or more nucleotides (characters A, C, G and T)
	# returns Hamming difference between strings (int)
	# returns -1 if errors are detected with the arguments
	
	# check that both arguments are supplied and are non-zero length strings
	if seq1.nil? || seq2.nil? || !(seq1.is_a?(String) && seq2.is_a?(String)) || seq1.length == 0 || seq2.length == 0
	    return -1
	end
        
	# convert to upper case
	upper_seq1 = seq1.upcase
	upper_seq2 = seq2.upcase
	
	# check that only A,C,G and T characters used
	if !(upper_seq1.match(/[ACGT]+/) && upper_seq2.match(/[ACGT]+/))
	   return -1
	end
	
	difference = 0
	for x in 0..upper_seq1.length - 1 do
	    break if x >= upper_seq2.length
	    difference += 1 unless upper_seq1[x] == upper_seq2[x]
	end
	return difference