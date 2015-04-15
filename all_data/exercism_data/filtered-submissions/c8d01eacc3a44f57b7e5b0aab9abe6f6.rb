def compute(strand_a, strand_b)





		hamming_distance = 0
		length_of_shortest_strand = [strand_a.length, strand_b.length].min
		(0...length_of_shortest_strand).each do |character_index|
		  if strand_a[character_index] != strand_b[character_index] then
			hamming_distance +=1
		  end
		end
		hamming_distance
	end