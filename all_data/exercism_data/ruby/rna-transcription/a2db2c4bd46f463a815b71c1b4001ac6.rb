class Complement
	
	COMPLEMENTS = {
		"G" => "C",
		"C" => "G",
		"T" => "A",
		"A"	=> "U"
	}

	def self.of_dna strand
		strand.chars.map { |acid| COMPLEMENTS[acid] }.join
	end

	def self.of_rna strand
		strand.chars.map { |acid| COMPLEMENTS.key(acid) }.join
	end
end
