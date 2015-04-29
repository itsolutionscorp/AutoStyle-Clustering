class Complement

	CONVERT = {
		'C' => 'G', 
		'G' => 'C',
		'T' => 'A',
		'A' => 'U',
	}

	def self.of_dna(dna)
		dna.gsub(/[GCTA]/, CONVERT)
	end

	def self.of_rna(rna)
		rna.gsub(/[CGAU]/, CONVERT.invert)
	end
	
end
