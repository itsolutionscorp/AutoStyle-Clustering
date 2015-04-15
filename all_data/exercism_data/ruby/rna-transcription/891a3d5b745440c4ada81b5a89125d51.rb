class Complement
	def self.of_dna(dna)
		rna = ""
		dna.each_char do |nucleotide|
			rna << TRANSCRIPTION[nucleotide]
		end
		rna
	end

	def self.of_rna(rna)
		dna = ""
		rna.each_char do |nucleotide|
			dna << TRANSCRIPTION.key(nucleotide)
		end
		dna
	end

	TRANSCRIPTION = {
		"C" => "G",
		"G" => "C",
		"A" => "U",
		"T" => "A"
	}
end
