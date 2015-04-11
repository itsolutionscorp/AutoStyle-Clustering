class Complement
	DNA_NUCLEOTIDES = 'GCTA'
	RNA_NUCLEOTIDES = 'CGAU'

	def self.of_dna str 
		str.tr!(DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
	end


	def self.of_rna str
		str.tr!(RNA_NUCLEOTIDES, DNA_NUCLEOTIDES)
	end

end
