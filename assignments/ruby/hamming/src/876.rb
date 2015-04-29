def compute firstDna, secondDna
		minimun = [firstDna.length,secondDna.length].min - 1
		hamming_distance_count = 0
		(0..minimun).each do | idx |
			hamming_distance_count+= 1 if firstDna[idx] != secondDna[idx]
		end
		hamming_distance_count
	end