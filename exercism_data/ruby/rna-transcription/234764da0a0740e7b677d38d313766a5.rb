class Complement
	def self.of_dna(dna_sequence)
		return dna_sequence.tr('GCTA', 'CGAU')
	end

	def self.of_rna(rna_sequence)
		return rna_sequence.tr('CGAU', 'GCTA')
	end
end
				
