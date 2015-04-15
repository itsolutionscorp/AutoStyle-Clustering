module Complement
extend self
		D_NUCLEOTIDES = [ 'G','C','T','A' ]
		R_NUCLEOTIDES = [ 'C','G','A','U' ]

		# PUblic: compares a strand of DNA, and replaces nucleotides with the
		#         comparable RNA nucleotide
		#
		# strand: string, repesenting DNS strand
		#
		# returns: comparable RNA
		def of_dna(strand)
			strand.tr(D_NUCLEOTIDES.to_s,R_NUCLEOTIDES.to_s)
		end

		def of_rna(strand)
			strand.tr(R_NUCLEOTIDES.to_s,D_NUCLEOTIDES.to_s)
		end

end
