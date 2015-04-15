class DNA
@@thymine='T'
@@uracil='U'

	def initialize(dna)
	 	@dna = dna
  	end

	def to_rna
		@dna.gsub(@@thymine, @@uracil)
	end

end
