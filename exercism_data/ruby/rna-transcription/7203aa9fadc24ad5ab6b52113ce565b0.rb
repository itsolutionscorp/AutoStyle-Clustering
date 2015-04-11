module Complement
	RNA_COMPLEMENT = "GCTA"
	DNA_COMPLEMENT = "CGAU"


	def self.of_dna(string)
		string.tr(RNA_COMPLEMENT, DNA_COMPLEMENT)
	end

	def self.of_rna(string)
		string.tr(DNA_COMPLEMENT, RNA_COMPLEMENT)
	end
end
