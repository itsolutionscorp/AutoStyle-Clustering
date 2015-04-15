class Complement
	
	STRAND = ['GCTA', 'CGAU']
	
	def self.of_dna nucleo
		nucleo.tr(*STRAND)
	end

	def self.of_rna nucleo
		nucleo.tr(*STRAND.reverse)
	end
end
