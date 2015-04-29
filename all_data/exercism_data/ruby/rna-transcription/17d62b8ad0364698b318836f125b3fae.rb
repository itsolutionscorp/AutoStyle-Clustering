class Complement
	@@pairs = {
				"G" => "C", 
				"C" => "G",
				"T" => "A", 
				"A" => "U"
			}

	def self.of_dna(strand)
		rna_comp = []
		strand.each_char do |nuc|
			rna_comp << @@pairs[nuc]
		end
		rna_comp.join
	end

	def self.of_rna(strand)
		dna_comp = []
		strand.each_char do |nuc|
			dna_comp << @@pairs.key(nuc)
		end
		dna_comp.join
	end
end
