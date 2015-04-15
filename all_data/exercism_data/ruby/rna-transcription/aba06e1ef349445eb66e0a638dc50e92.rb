class Complement

	DNA_SEQUENCE = "CGTA"
	RNA_SEQUENCE = "GCAU"

	class << self
		def of_dna(dna)	
			dna.tr(DNA_SEQUENCE,RNA_SEQUENCE)
		end

		def of_rna(rna)
			rna.tr(RNA_SEQUENCE,DNA_SEQUENCE)
		end
	end
end
