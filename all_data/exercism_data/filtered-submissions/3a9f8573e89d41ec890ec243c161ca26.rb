def compute(dna_a, dna_b)










		hamming_score = 0
		min_length = [dna_a.length, dna_b.length].min
		for char_index in 0..min_length-1 do
		  if dna_a[char_index] != dna_b[char_index] then
			hamming_score +=1
		  end
		end
		hamming_score
	end