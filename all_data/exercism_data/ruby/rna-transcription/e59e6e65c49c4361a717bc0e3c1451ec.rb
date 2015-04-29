class Complement
	DNA_TO_RNA = {
		'G' => 'C',
		'C' => 'G',
		'T' => 'A',
		'A' => 'U'
	}
	RNA_TO_DNA = {
		'C' => 'G',
		'G' => 'C',
		'A' => 'T',
		'U' => 'A'
	}

	def self.of_dna(strand)
		strand.chars.map {|nucleotide| DNA_TO_RNA[nucleotide]}.join
	end

	def self.of_rna(strand)
		strand.chars.map {|nucleotide| RNA_TO_DNA[nucleotide]}.join
	end
end
