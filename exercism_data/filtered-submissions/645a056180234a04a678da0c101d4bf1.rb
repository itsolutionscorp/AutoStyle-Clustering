def compute(seq1, seq2)
		total = 0
		seq1.split('').each_with_index do |base, index|
			break if index + 1 >= seq2.length
			total+=1 if base != seq2[index] and index < seq2.length			
		end
		total
	end