class Hamming
	class << self
		def compute(dna_one, dna_two)
			(0..dna_one.length - 1).select {|i| dna_one[i] != dna_two[i]}.count
		end
	end
end
