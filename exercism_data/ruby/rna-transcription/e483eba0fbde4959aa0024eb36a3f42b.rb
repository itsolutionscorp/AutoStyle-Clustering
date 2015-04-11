class Complement

	def self.convert_dna(dna)
		dna.tr('CGTA', 'GCAU')
	end

	def self.convert_rna(rna)
		rna.tr('GCAU', 'CGTA')
	end		

	def self.of_dna(strand)	
		self.convert_dna(strand)
	end

	def self.of_rna(strand)
		self.convert_rna(strand)
	end
end
