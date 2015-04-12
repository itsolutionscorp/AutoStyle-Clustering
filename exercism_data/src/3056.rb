class Hamming

	def compute(strand1, strand2)

		if strand1.length != strand2.length
			strand1 = strand1.slice(0..strand2.length-1)
		end
		strand1.chars.zip(strand2.chars).count { |a,b| a != b }
	end
end
