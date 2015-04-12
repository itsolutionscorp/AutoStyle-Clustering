def compute(seq_1, seq_2)
		min_seq_length = [seq_1.length, seq_2.length].min
		distance = 0

		(0...min_seq_length).each do |i|
			distance += 1 if seq_1[i] != seq_2[i]
		end
		distance
	end 
end