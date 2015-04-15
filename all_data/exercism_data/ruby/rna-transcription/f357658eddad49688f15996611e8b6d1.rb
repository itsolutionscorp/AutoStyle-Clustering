class Complement

	@@dna_to_rna = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

	def self.of_dna(dna)

		output = ''

		dna.chars { |dna| output << @@dna_to_rna[dna] }

		output

	end

	def self.of_rna(rna)

		output = ''

		rna.chars { |rna| output << @@dna_to_rna.key(rna) }

		output

	end

end
