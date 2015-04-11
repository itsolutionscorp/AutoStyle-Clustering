class Complement
	def self.of_dna strand 

		rna_matcher = { "C" => "G",
						"G" => "C",
						"T" => "A",
						"A" => "U" }

		rna = ""

		strand.each_char do |letter|
			rna << rna_matcher["#{letter}"]
		end

		return rna
	end

	def self.of_rna strand
		dna_matcher = { "G" => "C",
						"C" => "G",
						"A" => "T",
						"U" => "A"}

		dna = ""

		strand.each_char do |letter|
			dna << dna_matcher["#{letter}"]
		end
		return dna
	end
end
