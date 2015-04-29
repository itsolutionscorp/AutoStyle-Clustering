class DNA

	def initialize(dna_strand)
		@dna_strand = String(dna_strand)
	end

	def to_rna
		@rna_strand ||= dna_to_rna
	end

	def to_s
		dna_strand
	end


	private

	def dna_strand
		@dna_strand
	end

	def dna_to_rna
		dna_strand.gsub(/T/, 'U')
	end
end
