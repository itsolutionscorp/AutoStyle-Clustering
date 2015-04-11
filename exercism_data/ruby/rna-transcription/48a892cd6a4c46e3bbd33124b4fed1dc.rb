class Complement
	def self.of_dna(dnaSequence)
		return dnaSequence.tr('GCTA', 'CGAU')
	end

	def self.of_rna(rnaSequence)
		return rnaSequence.tr('CGAU', 'GCTA')
	end
end
				
