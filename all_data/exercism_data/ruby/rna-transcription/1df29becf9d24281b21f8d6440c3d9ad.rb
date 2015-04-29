class Complement

	def self.of_dna(dna)
		dna_to_rna = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
		dna.chars.collect{|char| dna_to_rna[char]}.join
	end

	def self.of_rna(rna)
		rna_to_dna = { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }
		rna.chars.collect{|char| dna_to_rna[char]}.join
	end
end
