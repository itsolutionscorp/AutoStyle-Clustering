class Complement
	
	def self.of_dna(rna_strand)
		rna_strand.downcase.gsub(/g/, 'C').gsub(/c/, 'G').gsub(/t/, 'A').gsub(/a/, 'U').upcase
	end

	def self.of_rna(dna_strand)
		dna_strand.downcase.gsub(/c/, 'G').gsub(/g/, 'C').gsub(/a/, 'T').gsub(/u/, 'A').upcase
	end
end
