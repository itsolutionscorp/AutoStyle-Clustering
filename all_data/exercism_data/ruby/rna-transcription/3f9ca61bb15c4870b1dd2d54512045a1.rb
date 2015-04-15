class Complement
	@dna_to_rna_index = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
	def self.of_dna(dna)
		find_complement(dna, :fetch)
	end

	def self.of_rna(rna)
		find_complement(rna, :key)
	end

	def self.find_complement(input, method)
		output = ''
		input.split("").each do |nucleotide|
			output  = output + @dna_to_rna_index.send(method, nucleotide)
		end
		output
	end
end
