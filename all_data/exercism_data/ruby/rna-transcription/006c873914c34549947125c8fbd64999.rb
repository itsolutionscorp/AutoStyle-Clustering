class Complement

	@@to_rna = {
		"G" => "C",
		"C" => "G",
		"T" => "A",
		"A" => "U"
	}

	def self.of_dna(strand)
		self.convert(strand,@@to_rna)
	end

	def self.of_rna(strand)
		self.convert(strand,@@to_rna.invert)
	end

	def self.convert(strand,cipher)
		strand.chars.map{
			|c| cipher[c]
		}.join
	end
end
