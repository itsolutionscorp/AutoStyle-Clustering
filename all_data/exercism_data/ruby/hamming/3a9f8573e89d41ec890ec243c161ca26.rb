class Hamming

	def self.compute(dna_a, dna_b)	
	
	# Given two DNA sequences, calculate the number of 
	# different pairs. If the sequences are of equal length, line them up by the 
	# leftmost character and ignore blanks. 
	# In other words, 
	#  Input: two DNA sequences A, B, where  A and B
	#         are made of letters in {A,C,T,G}
	#         n is the length of the shorter of the two strings.
	#  Output: from i=0 to 1-A.length, the number instances where A[i] != B[i]	
	
		hamming_score = 0
		min_length = [dna_a.length, dna_b.length].min
		for char_index in 0..min_length-1 do
		  if dna_a[char_index] != dna_b[char_index] then
			hamming_score +=1
		  end
		end
		hamming_score
	end# of self.compute
end# of Hamming
    
