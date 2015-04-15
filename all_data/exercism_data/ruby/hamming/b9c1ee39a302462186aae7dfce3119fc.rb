class Hamming
	MATCHED = "\\x00"

	def self.compute(dna1, dna2)
		dna1 = dna1[0..dna2.size-1] if dna1.size > dna2.size
		xor(dna1, dna2).sub(MATCHED, "").size / 4 # 4 is the number of chars per comparation
	end

	def self.xor(dna1, dna2)
		dna1.bytes.zip(dna2.bytes).map { |(a,b)| a ^ b }.pack('c*')
	end
end
