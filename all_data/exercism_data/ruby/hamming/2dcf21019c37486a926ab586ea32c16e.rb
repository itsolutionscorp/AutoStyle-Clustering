class Hamming

	def self.compute(strand1,strand2)
		strand1.chars.each_with_index.select { |g, i| g != strand2[i] }.length
	end

end