def complement(string,hash)
	complement = ''
	string.each_char do |char|
		complement << hash[char]
	end
	complement
end

class Complement

	def self.of_dna(strand)
		dna_to_rna = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

		complement(strand, dna_to_rna)
	end

	def self.of_rna(strand)
		rna_to_dna = {'G' => 'C', 'C' => 'G', 'A' => 'T', 'U' => 'A'}

		complement(strand, rna_to_dna)
	end

end
