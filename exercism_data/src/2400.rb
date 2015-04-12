class Hamming
	def compute (strand_a, strand_b)
		hamming_distance = 0
		shorter_length = [strand_a.length,strand_b.length].min
		shorter_length.times do |index|
			hamming_distance += strand_a[index] == strand_b[index] ? 0 : 1
		end
		return hamming_distance
	end
end
