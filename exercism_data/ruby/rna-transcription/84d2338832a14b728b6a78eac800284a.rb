class Complement

	DNA_TO_RNA_CONVERSION = {
		'G' => 'C',
		'C' => 'G',
		'T' => 'A',
		'A' => 'U'}

	def self.of_dna(dna)
		dna.chars.map { |nucleotide|
			DNA_TO_RNA_CONVERSION[nucleotide]
		}.join
	end

	def self.of_rna(rna)
		rna.chars.map { |nucleotide|
			DNA_TO_RNA_CONVERSION.key(nucleotide)
		}.join
	end

end
