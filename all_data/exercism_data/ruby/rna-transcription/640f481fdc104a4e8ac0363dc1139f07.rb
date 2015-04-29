class Complement
  def self.of_dna(dna_strand)
    DnaRnaSwapClass.new(dna_strand).toRna
  end

  def self.of_rna(rna_strand)
    DnaRnaSwapClass.new(rna_strand).toDna
  end
end

class DnaRnaSwapClass
	def initialize(strand)
		@strand = strand
	end

	def toRna
		@strand.tr(dna, rna)
	end

	def toDna
		@strand.tr(rna, dna)
	end

	private
		def dna
			"GCTA"
		end

		def rna
			"CGAU"		
		end
end
