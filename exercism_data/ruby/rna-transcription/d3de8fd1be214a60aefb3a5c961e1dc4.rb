module Conversion 

	def self.included(base)
		base.extend ClassMethods
	end

	module ClassMethods
		def of_dna(strand)
			nucleotide_conversion(strand).map { |nucleotide|
				convert(nucleotide) }.join
		end

		def nucleotide_conversion(strand)
			strand.chars
		end
	end

	def self.of_rna(strand)
		nucleotide_conversion(strand).map { |nucleotide|
			convert(nucleotide) }.join
	end

end

class DNAComplement
include Conversion
	def self.convert(dna)
		dna.tr('CGTA', 'GCAU')
	end
end

class RNAComplement
include Conversion
	def self.convert(rna)
		rna.tr('GCAU', 'CGTA')
	end
end
