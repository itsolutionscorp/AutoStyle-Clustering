class Complement
	DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

	def self.of_dna(strand)
		strand.chars.map { |c| DNA_TO_RNA[c] }.join
	end

	def self.of_rna(strand)
		strand.chars.map { |c| DNA_TO_RNA.invert[c] }.join
	end
end
