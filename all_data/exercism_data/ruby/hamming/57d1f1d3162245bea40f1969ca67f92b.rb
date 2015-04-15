class Hamming
	def self.compute (strand_a, strand_b)
		hamming_distance = 0
		shorter_length = [strand_a.length,strand_b.length].min
		(0..shorter_length -1).each do |index|
			if strand_a[index] != strand_b[index]
    			hamming_distance += 1
    		end
		end
		return hamming_distance
	end
end
