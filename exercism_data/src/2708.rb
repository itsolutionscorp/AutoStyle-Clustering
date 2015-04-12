def compute(strand1, strand2)
		hamming_distance = 0
		length = [strand1.length, strand2.length].min
		(0...length).each do |i|
			hamming_distance += 1 if strand1[i] != strand2[i]
		end
		return hamming_distance
	end