module Complement
extend self
	DNA_NUCLEOTIDES = [ 'G','C','T','A' ]
	RNA_NUCLEOTIDES = [ 'C','G','A','U' ]

	# PUblic: compares a strand of DNA, and replaces nucleotides with the
	#         comparable RNA nucleotide
	#
	# strand: string, repesenting DNS strand
	#
	# returns: comparable RNA
	def of_dna(strand)
		strand.tr(DNA_NUCLEOTIDES.to_s,RNA_NUCLEOTIDES.to_s)
	end

	def of_rna(strand)
		strand.tr(RNA_NUCLEOTIDES.to_s,DNA_NUCLEOTIDES.to_s)
	end

end
