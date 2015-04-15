class Hamming

	def self.compute(strand1, strand2)

		if strand1.length != strand2.length
			strand1 = strand1.slice(0..strand2.length-1)
			strand1.chars.zip(strand2.chars).count { |a,b| a != b }
		else
			strand1.chars.zip(strand2.chars).count { |a,b| a != b }
		end
	end
end
