class Complement

	def self.convert_dna(dna)
		dna.tr('CGTA', 'GCAU')
	end

	def self.convert_rna(rna)
		rna.tr('GCAU', 'CGTA')
	end		

	def self.nucleotide_conversion(strand)
		strand.chars
	end

	def self.of_dna(strand)	
		self.nucleotide_conversion(strand).map { |nucleotide|
			self.convert_dna(nucleotide) }.join
	end

	def self.of_rna(strand)
		self.nucleotide_conversion(strand).map { |nucleotide|
			self.convert_rna(nucleotide) }.join
	end
end
