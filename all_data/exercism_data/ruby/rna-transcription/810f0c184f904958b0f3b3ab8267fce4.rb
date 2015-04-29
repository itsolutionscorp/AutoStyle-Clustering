module Complement

	def self.of_dna(dna)
		dna.tr( DNA_VALUES, RNA_VALUES )
	end

	def self.of_rna(rna)
		rna.tr( RNA_VALUES, DNA_VALUES )
	end

	private
	DNA_VALUES = 'GCTA'
	RNA_VALUES = 'CGAU'
	
end
