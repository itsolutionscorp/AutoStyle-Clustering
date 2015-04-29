class Complement

	def self.of_dna(strand)
		dna_to_rna = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

		complement = ''
		
		strand.each_char do |char|
			complement << dna_to_rna[char]
		end
		complement
	end

	def self.of_rna(strand)
		rna_to_dna = {'G' => 'C', 'C' => 'G', 'A' => 'T', 'U' => 'A'}

		complement = ''
		
		strand.each_char do |char|
			complement << rna_to_dna[char]
		end
		complement
	end

end
