def compute(seq1=nil, seq2=nil)





	if seq1.nil? || seq2.nil? || !(seq1.is_a?(String) && seq2.is_a?(String)) || seq1.length == 0 || seq2.length == 0
	    return -1
	end


	upper_seq1 = seq1.upcase
	upper_seq2 = seq2.upcase


	if !(upper_seq1.match(/[ACGT]+/) && upper_seq2.match(/[ACGT]+/))
	   return -1
	end

	difference = 0
	for x in 0..upper_seq1.length - 1 do
	    break if x >= upper_seq2.length
	    difference += 1 unless upper_seq1[x] == upper_seq2[x]
	end
	return difference
    end