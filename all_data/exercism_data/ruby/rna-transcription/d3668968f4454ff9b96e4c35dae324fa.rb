class Complement 

	DNA_MAPPING = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

	def self.of_dna(dna)
		dna.chars.map { |a| build_dna(a) }.join
	end

	def self.of_rna(rna)
		rna.chars.map { |a| build_rna(a) }.join
	end

	private
	def self.build_dna(char)
		DNA_MAPPING[char]
	end

	def self.build_rna(char)
		DNA_MAPPING.invert[char]
	end
	

end
