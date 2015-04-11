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
		method = :convert_dna
		self.nucleotide_constructor(strand, method)
	end

	def self.of_rna(strand)
		method = :convert_rna
		self.nucleotide_constructor(strand, method)
	end
end
