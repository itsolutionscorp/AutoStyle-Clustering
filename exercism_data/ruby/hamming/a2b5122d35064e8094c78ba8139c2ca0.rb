class Hamming	

	def self.compute(dna_one, dna_two)

		errors = 0

		dna_one.chars.zip(dna_two.chars).each do |chr1, chr2|
			errors += 1 if chr1 != chr2
		end

		errors
	end
end
