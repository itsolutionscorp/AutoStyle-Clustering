class Complement

	DNA_COMPLEMENTS = Hash["G" => "C", "C" => "G", "T" => "A", "A" => "U"]

	def self.get_complements(strand, complements)
		complement = ""
		strand.split(//).each {|locus| complement += complements[locus]}
		#for locus in chars
		#	locus = complements[locus]
		#end
		complement
	end

	def Complement.of_dna(dna)
		self.get_complements(dna, DNA_COMPLEMENTS)
	end

	def Complement.of_rna(rna)
		self.get_complements(rna, DNA_COMPLEMENTS.invert)
	end

end
