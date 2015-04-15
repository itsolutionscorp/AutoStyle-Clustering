class Complement

	def self.convert_dna(dna)
		dna.tr('CGTA', 'GCAU')
	end

	def self.convert_rna(rna)
		rna.tr('GCAU', 'CGTA')
	end		

	def self.nucleotide_constructor(strand, method)
		strand.chars.map { |nucleotide| 
			send(method, nucleotide) }.join
	end

	def self.of_dna(strand)			
		self.nucleotide_constructor(strand, :convert_dna)
	end

	def self.of_rna(strand)
		self.nucleotide_constructor(strand, :convert_rna)
	end
end
